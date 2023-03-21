package com.example.fastfoodcity.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.fastfoodcity.MainActivity
import com.example.fastfoodcity.R
import com.example.fastfoodcity.databinding.FragmentEnterPhoneNumberBinding
import com.example.fastfoodcity.utilites.AUTH
import com.example.fastfoodcity.utilites.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment() {

    private var _binding: FragmentEnterPhoneNumberBinding? = null
    private val binding get() = _binding!!

    private lateinit var phoneNumberEditText: EditText
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onStart() {
        super.onStart()
        binding.nextBtn.setOnClickListener { sendCode() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        phoneNumberEditText = binding.enterNumberPhoneInput
        verificationCallbacks()
    }

    private fun sendCode() {
        if (phoneNumberEditText.text.toString().isEmpty() ||
            phoneNumberEditText.text.toString().length != 12) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            authUser()
        }
    }

    private fun authUser() {
        val options = PhoneAuthOptions.newBuilder(AUTH)
            .setPhoneNumber(phoneNumberEditText.text.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity as MainActivity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    /**
     * verificationCallBacks():
     * Обрабатывает релузьтаты запроса.
     * [onVerificationComplited] - в случае, если Task [isSuccessful] значит что пользователь авторизован
     * и происходит навигация в фрагмент [menuFragment].
     * В другом случае выводит Toast с сообщением об ошибке.
     * [onCodeSent] - запускается, когда было отправлено смс с кодом. Конструктор метода принимает [id] и [token]
     *
     */

    private fun verificationCallbacks() {
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }
}