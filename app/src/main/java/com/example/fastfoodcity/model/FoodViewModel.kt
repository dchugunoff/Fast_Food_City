package com.example.fastfoodcity.model

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
    val items: LiveData<List<Food>> = _foods

    private val _food = MutableLiveData<Food>()
    val item: LiveData<Food> = _food

    fun getItemList() {
        viewModelScope.launch {
            _status.value = FoodApiStatus.LOADING
            try {
                _foods.value = ItemApi.retrofitService.getItemList()
                _status.value = FoodApiStatus.DONE
            } catch (e: Exception) {
                _foods.value = emptyList()
                _status.value = FoodApiStatus.ERROR
            }
        }
    }
}