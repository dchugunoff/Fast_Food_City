package com.example.fastfoodcity.model

import com.example.fastfoodcity.network.Food

data class CartItem(
    val food: Food,
    var quantity: Int,
    var totalCost: Int
)