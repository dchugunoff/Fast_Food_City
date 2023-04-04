package com.example.fastfoodcity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fastfoodcity.databinding.ListCartViewBinding
import com.example.fastfoodcity.model.CartItem
import com.example.fastfoodcity.model.CartViewModel


class CartAdapter(
    var cartItems: MutableList<CartItem>, val viewModel: CartViewModel) :
    RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    class CartItemViewHolder(private val binding: ListCartViewBinding, val viewModel: CartViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            binding.apply {
                binding.cartItem = cartItem
                minusButton.setOnClickListener {
                    viewModel.decreaseQuantity(cartItem)
                }
                plusButton.setOnClickListener {
                    viewModel.increaseQuantity(cartItem)
                }
                binding.executePendingBindings()
                }
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListCartViewBinding.inflate(layoutInflater, parent, false)
        return CartItemViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
    }

}