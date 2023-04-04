package com.example.fastfoodcity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fastfoodcity.R
import com.example.fastfoodcity.databinding.FragmentEnterCodeBinding
import com.example.fastfoodcity.utilites.*
import com.google.firebase.auth.PhoneAuthProvider


class EnterCodeFragment: Fragment() {

    companion object {
        const val ARG_ID = "id"
    }
    private var id: String? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.verifyCode.addTextChangedListener(AppTextWatcher{
            val inputCode = binding.verifyCode.text.toString()
            if (inputCode.length == 6) {
                enterCode()
            }
        })

    }

    private fun enterCode() {
        val code = binding.verifyCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(id.toString(), code)
        val navController = findNavController()
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener { task2 ->
                        if (task2.isSuccessful) {
                            hideKeyboard(requireContext(), requireView())
                            showToast("Добро пожаловать!")
                            navController.popBackStack(R.id.menuFragment, false)
                            navController.navigate(R.id.menuFragment)
                        } else showToast(task2.exception?.message.toString())
                    }
            } else showToast(task.exception?.message.toString())
        }

    }

}