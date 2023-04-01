package com.example.fastfoodcity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fastfoodcity.databinding.ListViewItemBinding
import com.example.fastfoodcity.network.Food

class FoodListAdapter(val clickListener: FoodListener) :
    ListAdapter<Food, FoodListAdapter.FoodViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Food>(){
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.description == newItem.description &&
                    oldItem.price == newItem.price &&
                    oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    class FoodViewHolder(
        val binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: FoodListener, food: Food) {
            binding.food = food
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = getItem(position)
        holder.bind(clickListener, food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FoodViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

}

class FoodListener(val clickListener: (food: Food) -> Unit) {
    fun onClick(food: Food) = clickListener(food)
}
