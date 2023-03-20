package com.example.fastfoodcity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fastfoodcity.databinding.FragmentEnterCodeBinding
import com.example.fastfoodcity.utilites.AppTextWatcher


class EnterCode : Fragment() {

    private var _binding: FragmentEnterCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterCodeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.verifyCode.addTextChangedListener(AppTextWatcher{
            val inputCode = binding.verifyCode.text.toString()
            if (inputCode.length == 4) {
                verifyCode()
            }
        })
    }

    fun verifyCode() {
        Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show()
    }



}