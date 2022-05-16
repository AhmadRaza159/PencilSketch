package com.example.pencilsketch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val afterSplash = Intent(this, MainActivity::class.java)
            startActivity(afterSplash)
//            afterSplash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish()
        }, 1000)
    }
}