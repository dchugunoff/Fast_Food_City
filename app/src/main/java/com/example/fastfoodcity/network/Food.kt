package com.example.fastfoodcity.network

import com.squareup.moshi.Json

/**
 * Дата класс для создания объектов еды.
 */
data class Food(
    val name: String,
    val description: String,
    val price: String,
    @Json(name = "img") val imgSrcUrl: String
)