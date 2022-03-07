package com.jolla.intent_filter_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jolla.intent_filter_test.databinding.ActivityMainBinding
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            var sum = 0L
            val time = measureTimeMillis {
                for (i in 1..9_000_000_000) {
                    sum += i
                }
            }
            Log.d("jolla", "time : $time")
            binding.resultView.text = "sum : $sum"
        }
    }
}