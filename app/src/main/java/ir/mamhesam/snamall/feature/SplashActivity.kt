package ir.mamhesam.snamall.feature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ir.mamhesam.snamall.MainActivity
import ir.mamhesam.snamall.MainViewModel
import ir.mamhesam.snamall.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },4500)

    }
}