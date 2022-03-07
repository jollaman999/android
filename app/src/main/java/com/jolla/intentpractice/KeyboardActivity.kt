package com.jolla.intentpractice

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(
                    WindowInsets.Type.statusBars() or
                            WindowInsets.Type.navigationBars()
                )
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}