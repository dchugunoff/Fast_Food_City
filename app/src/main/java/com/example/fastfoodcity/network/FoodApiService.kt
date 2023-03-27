package com.example.fastfoodcity.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Устанавливаем базовый URL для Api
 */
private const val BASE_URL = "http://www.fastfoodcity.site/api/"

/**
 * Создаем экземпляр Moshi и регистрируем адаптер KotlinJsonAdapterFactory
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Создаем экземпляр Retrofit с помощью базового Url и MoshiConverterFactory
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Функция-запрос к Api.
 * suspend - означает, что она может приостанавливать свое выполнение без блокировки потока во время ожидания ответа от сервера.
 */
interface FoodApiService {
    @GET("getproducts")
    suspend fun getItemList(): List<Food>
}

/**
 * Создаем объект который содержит ссылку на объект Retrofit
 */
object ItemApi {
    val retrofitService : FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}