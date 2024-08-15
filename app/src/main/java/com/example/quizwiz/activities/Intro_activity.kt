package com.example.quizwiz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizwiz.R
import com.google.firebase.auth.FirebaseAuth

class intro_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            Toast.makeText(this , "Already login" , Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }
        val startBtn = findViewById<Button>(R.id.btnGetStarted)

        startBtn.setOnClickListener{
            redirect("LOGIN")
        }
    }

    private fun redirect(name:String){
        intent = when(name){
            "LOGIN" -> Intent(this , login_activity::class.java)
            "MAIN" -> Intent(this , MainActivity::class.java)
            else -> throw Exception("no path exist")
        }
        startActivity(intent)
        finish()
    }
}