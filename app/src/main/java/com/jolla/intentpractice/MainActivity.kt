package com.jolla.intentpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var text_second_result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requqestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == Activity.RESULT_OK) {
                val second_result = it.data?.getStringExtra("second_result")
                text_second_result.setText(second_result)
            }
        }

        val Button : Button = findViewById(R.id.intent_button)
        Button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("jolla", "Hello, jolla!")
            // startActivityForResult(intent, 999)
            requqestLauncher.launch(intent)
        }

        text_second_result = findViewById(R.id.second_result)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 999 && resultCode == Activity.RESULT_OK) {
//            val second_result = data?.getStringExtra("second_result")
//            text_second_result.setText(second_result)
//        }
//    }
}