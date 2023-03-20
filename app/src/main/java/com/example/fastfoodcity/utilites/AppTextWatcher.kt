package com.example.fastfoodcity.utilites

import android.text.Editable
import android.text.TextWatcher

/**
 * Функция [onSuccess] принимает объект [Editable] и ничего не возвращает([Unit]).
 */
class AppTextWatcher(val onSuccess: (Editable?) -> Unit): TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        onSuccess(s)
    }
}