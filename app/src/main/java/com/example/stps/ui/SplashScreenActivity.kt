package com.example.stps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    private val splashTime = 3000L
    private lateinit var myHandler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        myHandler = Handler()

        myHandler.postDelayed({
            goToStartActivity()
        }, splashTime)

    }

    // StartActivity 이동
    private fun goToStartActivity() {
        startActivity(Intent(applicationContext, StartActivity::class.java))
        finish()
    }
}
