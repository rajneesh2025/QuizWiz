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

class signup_activity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth = FirebaseAuth.getInstance()

        val signupBtn = findViewById<Button>(R.id.btnSignup)

        signupBtn.setOnClickListener{
            signUpUser()
        }

        val logintxt = findViewById<TextView>(R.id.btnLogin)
        logintxt.setOnClickListener{
            intent = Intent(this , login_activity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signUpUser(){
        val email : String = findViewById<EditText>(R.id.etEmailAddress).text.toString()
        val pass : String = findViewById<EditText>(R.id.etPassword).text.toString()
        val cnfPass: String = findViewById<EditText>(R.id.etCnfPassword).text.toString()

        if(email.isBlank() || pass.isBlank() || cnfPass.isBlank()){
            Toast.makeText(this , "Email or Password can't be blank" , Toast.LENGTH_SHORT).show()
            return
        }

        if(pass != cnfPass){
            Toast.makeText(this , "Password and Confirm Password should be same" , Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email , pass)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    //code
                    Toast.makeText(this , "Signup successfully!" , Toast.LENGTH_SHORT).show()

                    intent = Intent(this , MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    //code
                    Toast.makeText(this , "Unable to signup at this moment" , Toast.LENGTH_SHORT).show()
                }
            }

    }
}