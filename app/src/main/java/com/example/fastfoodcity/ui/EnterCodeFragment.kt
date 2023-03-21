package com.example.fastfoodcity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fastfoodcity.R
import com.example.fastfoodcity.databinding.FragmentEnterCodeBinding
import com.example.fastfoodcity.utilites.AUTH
import com.example.fastfoodcity.utilites.AppTextWatcher
import com.example.fastfoodcity.utilites.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider


class EnterCodeFragment(val id: String) : Fragment() {

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
        var id = arguments?.getString("id")

        binding.verifyCode.addTextChangedListener(AppTextWatcher{
            val inputCode = binding.verifyCode.text.toString()
            if (inputCode.length == 6) {
                enterCode()
            }
        })

    }

    private fun enterCode() {
        val code = binding.verifyCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                showToast("Добро пожаловать!")
                findNavController().navigate(R.id.menuFragment)
            } else showToast("id = $id")
        }


    }
}