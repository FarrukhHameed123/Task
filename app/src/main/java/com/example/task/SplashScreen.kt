package com.example.task

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val SPLASH_TIME_OUT = 3000
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}