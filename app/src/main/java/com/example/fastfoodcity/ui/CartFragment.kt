package com.example.fastfoodcity.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.fastfoodcity.R
import com.example.fastfoodcity.adapter.CartAdapter
import com.example.fastfoodcity.databinding.FragmentCartBinding
import com.example.fastfoodcity.model.CartViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

//        // Наблюдение за изменениями элементов корзины LiveData и обновление адаптера
//        cartViewModel.cartItem.observe(viewLifecycleOwner) { cartItems ->
//            cartAdapter.cartItems = cartItems
//            isEmpty()
//            cartAdapter.notifyDataSetChanged()
//        }
//
//        cartViewModel.summaryCost.observe(viewLifecycleOwner) { summaryCost ->
//            binding.tvTotalCost.text = resources.getString(R.string.price_with_currency, summaryCost)
//            binding.paymentButton.text = resources.getString(R.string.text_payment_button, summaryCost)
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clearButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.title_clear_dialog))
                .setMessage(resources.getString(R.string.supporting_text_clear_dialog))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->

                }
                .setPositiveButton(resources.getString(R.string.clear_tv_button)) { dialog, which ->
                    cartViewModel.clearCart()
                    isEmpty()
                }
                .show()
        }

        // Наблюдение за изменениями элементов корзины LiveData и обновление адаптера
        cartViewModel.cartItem.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.cartItems = cartItems
            isEmpty()
            cartAdapter.notifyDataSetChanged()
        }

        cartViewModel.summaryCost.observe(viewLifecycleOwner) { summaryCost ->
            binding.tvTotalCost.text = resources.getString(R.string.price_with_currency, summaryCost)
            binding.paymentButton.text = resources.getString(R.string.text_payment_button, summaryCost)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun isEmpty() {
        if (cartViewModel.isEmpty()) {
            binding.cartTv.visibility = View.GONE
            binding.clearButton.visibility = View.GONE
            binding.cartRecyclerView.visibility = View.GONE
            binding.linearLayoutCompat.visibility = View.GONE
            binding.paymentButton.visibility = View.GONE
            binding.tvIsEmpty.visibility = View.VISIBLE
        } else {
            binding.tvIsEmpty.visibility = View.GONE
        }
    }

}
