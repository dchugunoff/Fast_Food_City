package com.example.fastfoodcity.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fastfoodcity.R
import com.example.fastfoodcity.databinding.FragmentEnterPhoneNumberBinding


class EnterPhoneNumber : Fragment() {

    private var _binding: FragmentEnterPhoneNumberBinding? = null
    private val binding get() = _binding!!


    override fun onStart() {
        super.onStart()
        binding.nextBtn.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.enterNumberPhoneInput.text.toString().isEmpty()) {
            Toast.makeText(context, getString(R.string.register_toast_enter_phone), Toast.LENGTH_SHORT).show()
        } else {
            findNavController().navigate(R.id.action_enterPhoneNumber_to_enterCode)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}