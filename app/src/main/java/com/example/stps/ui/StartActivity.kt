package com.example.stps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val btLogin = findViewById<Button>(R.id.button) // 로그인 버튼
        val btSignup = findViewById<TextView>(R.id.button2) // 회원가입 버튼

        // 로그인 버튼 클릭 시 로그인 화면으로 이동
        btLogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))

        }

        // 회원가입 버튼 클릭 시 회원가입 화면으로 이동
        btSignup.setOnClickListener {
            startActivity(Intent(applicationContext, PhoneNumberVerificationActivity::class.java))
        }
    }
}
