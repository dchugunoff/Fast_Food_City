package com.example.fastfoodcity.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fastfoodcity.network.Food

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItem: LiveData<MutableList<CartItem>> = _cartItems

    private val _summaryCost = MutableLiveData<Int>()
    val summaryCost: LiveData<Int> = _summaryCost


    fun addToCart(food: Food) {
        val cartItem = _cartItems.value?.find { it.food == food }
        if (cartItem != null) {
            cartItem.quantity++
            cartItem.totalCost = cartItem.quantity * cartItem.food.price.toInt()
            _summaryCost.value = getTotalCost()
        } else {
            _cartItems.value?.add(CartItem(food, 1, food.price.toInt()))
            _summaryCost.value = getTotalCost()
        }
    }

    fun decreaseQuantity(cartItem: CartItem) {
        if (cartItem.quantity > 1) {
            cartItem.quantity--
            cartItem.totalCost = cartItem.quantity * cartItem.food.price.toInt()
            _cartItems.value = _cartItems.value
            _summaryCost.value = getTotalCost()
        } else {
            _cartItems.value?.remove(cartItem)
            _cartItems.value = _cartItems.value
            _summaryCost.value = getTotalCost()
        }
    }
    fun increaseQuantity(cartItem: CartItem) {
        cartItem.quantity++
        cartItem.totalCost = cartItem.quantity * cartItem.food.price.toInt()
        _cartItems.value = _cartItems.value
        _summaryCost.value = getTotalCost()
    }

    private fun getTotalCost(): Int {
        var summaryCost = 0
        for (cartItem in _cartItems.value ?: mutableListOf()) {
            summaryCost += cartItem.totalCost
        }
        return summaryCost
    }

    fun clearCart() {
        _cartItems.value?.clear()
        _summaryCost.value = 0
        _cartItems.value = _cartItems.value
    }

    fun isEmpty(): Boolean {
        return _cartItems.value?.isEmpty() ?: true
    }


}