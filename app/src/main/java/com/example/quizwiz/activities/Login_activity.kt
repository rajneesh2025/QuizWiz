package com.example.quizwiz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizwiz.R
import com.google.firebase.auth.FirebaseAuth

class login_activity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseAuth = FirebaseAuth.getInstance()

        val loginBtn = findViewById<Button>(R.id.btnLogin)

        loginBtn.setOnClickListener{
            login()
        }

        val signuptxt = findViewById<TextView>(R.id.btnSignup)
        signuptxt.setOnClickListener{
            intent = Intent(this , signup_activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(){
        val email : String = findViewById<EditText>(R.id.etEmailAddress).text.toString()
        val pass : String = findViewById<EditText>(R.id.etPassword).text.toString()

        if(email.isBlank() || pass.isBlank()){
            Toast.makeText(this , "Email or Password can't be blank"  , Toast.LENGTH_SHORT).show()
                return
        }


        firebaseAuth.signInWithEmailAndPassword(email , pass)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this , "Success"  , Toast.LENGTH_SHORT).show()
                    intent = Intent(this , MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this , "Failed"  , Toast.LENGTH_SHORT).show()
                }
            }
    }
}