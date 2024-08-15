package com.example.quizwiz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizwiz.R
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity(){
    lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseAuth = FirebaseAuth.getInstance()
        findViewById<TextView>(R.id.userEmail).text = firebaseAuth.currentUser?.email

        findViewById<LinearLayout>(R.id.btnLogout).setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            intent = Intent(this , login_activity::class.java)
            startActivity(intent)

            finish()
        }
    }
}