package com.example.fastfoodcity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import com.example.fastfoodcity.R
import com.example.fastfoodcity.databinding.FragmentFoodDetailBinding
import com.example.fastfoodcity.model.FoodViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FoodDetailFragment : BottomSheetDialogFragment() {

    private val viewModel: FoodViewModel by activityViewModels()

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


        }
        return binding.root
    }

}