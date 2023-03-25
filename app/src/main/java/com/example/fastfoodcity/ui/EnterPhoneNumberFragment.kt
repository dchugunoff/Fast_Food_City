package com.example.fastfoodcity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fastfoodcity.MainActivity
import com.example.fastfoodcity.R
import com.example.fastfoodcity.databinding.FragmentEnterPhoneNumberBinding
import com.example.fastfoodcity.utilites.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment() {

    private var _binding: FragmentEnterPhoneNumberBinding? = null
    private val binding get() = _binding!!

    private lateinit var phoneNumberEditText: EditText
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var animation: Animation



    /**
     * Обрабатывает релузьтаты запроса.
     * [onVerificationComplited] - в случае, если Task [isSuccessful] значит что пользователь авторизован
     * и происходит навигация в фрагмент [menuFragment].
     * В другом случае выводит Toast с сообщением об ошибке.
     * [onCodeSent] - запускается, когда было отправлено смс с кодом. Конструктор метода принимает [id] и [token]
     *
     */
    override fun onStart() {
        super.onStart()
        animation = AnimationUtils.loadAnimation(context, R.anim.animation_reg_nextbtn)
        phoneNumberEditText = binding.enterNumberPhoneInput
        binding.nextBtn.visibility = View.GONE
        buttonAnimation()
        binding.nextBtn.setOnClickListener { sendCode() }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast("Добро пожаловать!")
                        findNavController().navigate(R.id.action_enterPhoneNumber_to_menuFragment)
                    } else showToast(it.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                var bundle = Bundle()
                bundle.putString("id", id)
                val navController = findNavController()
                navController.navigate(R.id.enterCode, bundle)
            }
        }
    }


    private fun sendCode() {
        if (phoneNumberEditText.text.toString().isEmpty() ||
            phoneNumberEditText.text.toString().length != 10) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            hideKeyboard(requireContext(), requireView())
            authUser()
            findNavController().navigate(R.id.action_enterPhoneNumber_to_enterCode)
        }
    }

    private fun authUser() {
        val options = PhoneAuthOptions.newBuilder(AUTH)
            .setPhoneNumber("+1" + phoneNumberEditText.text.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity as MainActivity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun buttonAnimation() {
        binding.enterNumberPhoneInput.addTextChangedListener(AppTextWatcher {
            val inputPhone = binding.enterNumberPhoneInput.text.toString()
            if (inputPhone.length == 10) {
                binding.nextBtn.visibility = View.VISIBLE
                binding.nextBtn.startAnimation(animation)
            } else {
                binding.nextBtn.visibility = View.GONE // скрыть кнопку, если длина введенного текста меньше 10 символов
            }
        })
    }


}