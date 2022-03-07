package com.jolla.intent_filter_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.jolla.intent_filter_test.databinding.ActivityMainBinding
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = object: Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                binding.resultView.text = "sum : ${msg.arg1}"
            }
        }

        binding.btnClick.setOnClickListener {
            thread {
                var sum = 0L
                val time = measureTimeMillis {
                    for (i in 1..9_000_000_000) {
                        sum += i
                    }
                    val message = Message()
                    message.arg1 = sum.toInt()
                    handler.sendMessage(message)
                }
                Log.d("jolla", "time : $time")
            }
        }
    }
}