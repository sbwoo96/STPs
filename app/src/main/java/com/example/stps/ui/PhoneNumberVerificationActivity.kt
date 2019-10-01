package com.example.stps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PhoneNumberVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number_verification)

        val btBefore = findViewById<Button>(R.id.button9) // 이전 버튼
        val btNext = findViewById<Button>(R.id.button10) // 다음 버튼

        // 이전 버튼 클릭 시 StartActivity로 이동
        btBefore.setOnClickListener {
            finish() // 이전 화면이 StartActivity 이기 때문에 finish 날림
        }

        // 다음 버튼 클릭 시 AccountRegistrationActivity로 이동
        btNext.setOnClickListener {
            val intent = Intent(applicationContext, AccountRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
