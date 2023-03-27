package com.example.fastfoodcity.network

/**
 * Дата класс для создания объектов еды.
 */
data class Food(
    val name: String,
    val description: String,
    val price: Int,
    val img: String
)