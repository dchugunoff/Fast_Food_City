package com.example.fastfoodcity.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.fastfoodcity.R
import com.example.fastfoodcity.adapter.FoodListAdapter
import com.example.fastfoodcity.adapter.FoodListener
import com.example.fastfoodcity.databinding.FragmentMenuBinding
import com.example.fastfoodcity.model.FoodViewModel


class MenuFragment : Fragment() {

    private val viewModel: FoodViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMenuBinding.inflate(inflater)
        viewModel.getFoodList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = FoodListAdapter(FoodListener { food ->
            viewModel.onFoodClicked(food)
            findNavController()
                .navigate(R.id.action_menuFragment_to_foodDetailFragment)
        })
        return binding.root
    }
}