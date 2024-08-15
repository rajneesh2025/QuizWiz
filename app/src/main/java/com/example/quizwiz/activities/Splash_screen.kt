package com.example.quizwiz.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizwiz.R

class splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.textLoginSubHeader).animate().translationY(1200f).setDuration(1800)
        findViewById<ImageView>(R.id.imageView).animate().translationY(1200f).setDuration(1800)
        findViewById<TextView>(R.id.txtLoginHeader).animate().translationY(1200f).setDuration(1800)
        val btn = findViewById<Button>(R.id.button)

        Handler().postDelayed({

//            intent = Intent(this  , intro_activity::class.java)
//            startActivity(intent)
//            finish()
            btn.alpha = 1F
        },2500)

    }
}