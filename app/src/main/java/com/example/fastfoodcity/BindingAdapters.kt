package com.example.fastfoodcity

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.example.fastfoodcity.model.FoodApiStatus
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fastfoodcity.adapter.FoodListAdapter
import com.example.fastfoodcity.network.Food

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Food>) {
    val adapter = recyclerView.adapter as FoodListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        //.toUrli() - для преобразования URL в URI
        //.buildUpon().scheme("https") для использования схемы HTTPS
        //.build() - для создания объекта
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        //.load для загрузки изображения из объекта imgUrl в imgView(библиотека Coil)
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.broken_image)
        }
    }
}

@BindingAdapter("foodApiStatus")
fun bindStatus(statusImageView: ImageView, status: FoodApiStatus) {
    when(status) {
        FoodApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FoodApiStatus.DONE -> {
            statusImageView.visibility = View.VISIBLE
        }
        FoodApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}