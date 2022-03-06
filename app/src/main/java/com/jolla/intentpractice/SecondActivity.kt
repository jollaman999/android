package com.jolla.intentpractice

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btn_close : Button = findViewById(R.id.btn_close)
        btn_close.setOnClickListener {
            intent.putExtra("second_result", "Hello, from Second!")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        val intent_jolla = intent.getStringExtra("jolla")
        val test_result: TextView = findViewById(R.id.intent_data_text)

        test_result.text = intent_jolla
        test_result.text = intent.data.toString()

        Toast.makeText(this, "ACTION_EDIT Data: " + intent.data.toString(), Toast.LENGTH_SHORT).show()
    }
}