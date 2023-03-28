package com.example.fastfoodcity.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfoodcity.network.Food
import com.example.fastfoodcity.network.ItemApi
import kotlinx.coroutines.launch

enum class FoodApiStatus { LOADING, ERROR, DONE }

class FoodViewModel : ViewModel() {
    private val _status = MutableLiveData<FoodApiStatus>()
    val status: LiveData<FoodApiStatus> = _status

    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    private val _food = MutableLiveData<Food>()
    val food: LiveData<Food> = _food

    fun getFoodList() {
        viewModelScope.launch {
            _status.value = FoodApiStatus.LOADING
            try {
                _foods.value = ItemApi.retrofitService.getFoodList()
                _status.value = FoodApiStatus.DONE
                Log.d("FoodViewModel", "Got food list: ${_foods.value}")
            } catch (e: Exception) {
                _foods.value = emptyList()
                _status.value = FoodApiStatus.ERROR
                Log.e("FoodViewModel", "Error getting food list: ${e.message}")
            }
        }
    }

    fun onFoodClicked(food: Food) {
        _food.value = food
    }
}