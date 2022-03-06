package com.jolla.intentpractice

import android.hardware.input.InputManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.jolla.intentpractice.databinding.ActivityKeyboardBinding

class KeyboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityKeyboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manageer = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.btnShow.setOnClickListener {
            binding.et.requestFocus()
            manageer.showSoftInput(binding.et, InputMethodManager.SHOW_IMPLICIT)
        }
        binding.btnHide.setOnClickListener {
            binding.et.requestFocus()
            manageer.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}