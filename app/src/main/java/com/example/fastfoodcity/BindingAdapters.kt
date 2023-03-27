package com.example.fastfoodcity

import android.view.View
import android.widget.ImageView
import com.example.fastfoodcity.model.FoodApiStatus

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