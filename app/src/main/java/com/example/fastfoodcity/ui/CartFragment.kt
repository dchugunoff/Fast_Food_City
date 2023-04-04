package com.example.fastfoodcity.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.fastfoodcity.R
import com.example.fastfoodcity.adapter.CartAdapter
import com.example.fastfoodcity.databinding.FragmentCartBinding
import com.example.fastfoodcity.model.CartViewModel

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by activityViewModels()

    private lateinit var cartAdapter: CartAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        //Адаптер с пустым списком
        cartAdapter = CartAdapter(mutableListOf(), cartViewModel)

        // Устанавливаем адаптер recyclerView
        binding.cartRecyclerView.adapter = cartAdapter

        // Наблюдение за изменениями элементов корзины LiveData и обновление адаптера
        cartViewModel.cartItem.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.cartItems = cartItems
            cartAdapter.notifyDataSetChanged()
        }

        cartViewModel.summaryCost.observe(viewLifecycleOwner) { summaryCost ->
            binding.tvTotalCost.text = resources.getString(R.string.price_with_currency, summaryCost)
            binding.paymentButton.text = resources.getString(R.string.text_payment_button, summaryCost)
        }


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}