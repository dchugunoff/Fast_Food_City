package com.example.fastfoodcity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fastfoodcity.databinding.ListCartViewBinding
import com.example.fastfoodcity.model.CartItem


class CartAdapter(
    var cartItems: MutableList<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    class CartItemViewHolder(private val binding: ListCartViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            binding.apply {
                binding.cartItem = cartItem
                binding.executePendingBindings()
                }
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListCartViewBinding.inflate(layoutInflater, parent, false)
        return CartItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
    }

}