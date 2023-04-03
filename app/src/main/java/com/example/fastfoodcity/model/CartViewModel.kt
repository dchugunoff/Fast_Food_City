package com.example.fastfoodcity.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fastfoodcity.network.Food

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItem: LiveData<MutableList<CartItem>> = _cartItems

    fun addToCart(food: Food) {
        val cartItem = _cartItems.value?.find { it.food == food }
        if (cartItem != null) {
            cartItem.quantity++
            cartItem.totalCost = cartItem.quantity * cartItem.food.price.toInt()
        } else {
            _cartItems.value?.add(CartItem(food, 1, food.price.toInt()))
        }
    }

    fun removeFromCart(food: Food) {
        val cartItem = _cartItems.value?.find { it.food == food }
        if (cartItem != null) {
            if(cartItem.quantity > 1) {
                cartItem.quantity--
                cartItem.totalCost = cartItem.quantity * cartItem.food.price.toInt()
            } else {
                _cartItems.value?.remove(cartItem)
            }
        }
    }
}