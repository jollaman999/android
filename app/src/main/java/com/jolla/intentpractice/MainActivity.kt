package com.jolla.intentpractice

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.lang.Exception

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

        // SecondActivity
        val btn_intent: Button = findViewById(R.id.btn_intent)
        btn_intent.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("jolla", "Hello, jolla!")
            // startActivityForResult(intent, 999)
            requqestLauncher.launch(intent)
        }
        text_second_result = findViewById(R.id.second_result)

        // KeyboardActivity
        val btn_keyboard: Button = findViewById(R.id.btn_keyboard)
        btn_keyboard.setOnClickListener {
            val intent = Intent(this, KeyboardActivity::class.java)
            startActivity(intent)
        }

        // 인텐트 필터 실습 1
        val et_phone_number: EditText = findViewById(R.id.et_phone_number)
        val btn_call_phone: Button = findViewById(R.id.btn_call_phone)
        btn_call_phone.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_CALL
            intent.data = Uri.parse("tel:" + et_phone_number.text.toString())
            startActivity(intent)
        }

        // 인텐트 필터 실습 2
        val et_www: EditText = findViewById(R.id.et_www)
        val btn_action_edit: Button = findViewById(R.id.btn_action_edit)
        btn_action_edit.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_EDIT
            intent.data = Uri.parse("http://" + et_www.text.toString())
            startActivity(intent)
        }

        // 인텐트 필터 실습 3
        val btn_action_hello: Button = findViewById(R.id.btn_action_hello)
        btn_action_hello.setOnClickListener {
            val intent = Intent()
            intent.action = "ACTION_HELLO"
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "No activity found to handle 'ACTION_HELLO'",
                    Toast.LENGTH_SHORT).show()
            }
        }

        // 인텐트 필터 실습 4
        val btn_pkg: Button = findViewById(R.id.btn_pkg)
        btn_pkg.setOnClickListener {
            val intent = Intent()
            val pkg = "com.jolla.intent_filter_test"
            intent.action = Intent.ACTION_VIEW
            intent.`package` = pkg
            startActivity(intent)
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 999 && resultCode == Activity.RESULT_OK) {
//            val second_result = data?.getStringExtra("second_result")
//            text_second_result.setText(second_result)
//        }
//    }
}