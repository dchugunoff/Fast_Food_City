package com.example.fastfoodcity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import com.example.fastfoodcity.R
import com.example.fastfoodcity.databinding.FragmentFoodDetailBinding
import com.example.fastfoodcity.model.CartViewModel
import com.example.fastfoodcity.model.FoodViewModel
import com.example.fastfoodcity.network.Food
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FoodDetailFragment : BottomSheetDialogFragment() {

    private val viewModel: FoodViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        dialog?.setOnShowListener {

            val root = dialog!!.findViewById<CoordinatorLayout>(R.id.container_root)
            val bottomSheetInternal = root.findViewById<ConstraintLayout>(R.id.bottom_sheet)

            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetInternal)
            bottomSheetBehavior.isHideable = false
            BottomSheetBehavior.from(root.parent as View).peekHeight = root.height
            bottomSheetBehavior.peekHeight = root.height
            root.parent.requestLayout()

            binding.btnAddToCart.setOnClickListener {
                val food = viewModel.food.value
                if(food != null){
                    cartViewModel.addToCart(food)
                    dismiss()
                }
            }

        }
        return binding.root
    }

}