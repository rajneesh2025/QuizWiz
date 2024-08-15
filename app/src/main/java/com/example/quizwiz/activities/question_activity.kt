package com.example.quizwiz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizwiz.R
import com.example.quizwiz.adapters.optionAdapter
import com.example.quizwiz.models.Questions
import com.example.quizwiz.models.Quiz
import com.example.quizwiz.utils.colorPicker
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class question_activity : AppCompatActivity() {
    var quizes :MutableList<Quiz>? = null
    var questions:MutableMap<String , Questions> ? = null
    var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpFireStore()
        setUpEventListeners()
    }

    private fun setUpEventListeners() {
        findViewById<Button>(R.id.btnPrev).setOnClickListener{
            index--
            if(index == questions!!.size){
                index--
            }
            bindViews()
        }
        findViewById<Button>(R.id.btnNext).setOnClickListener{
            index++
            bindViews()
        }
        findViewById<Button>(R.id.btnSubmit).setOnClickListener{
            Log.d("FINALQUIZ" , questions.toString())

            intent = Intent(this , ResultActivity::class.java)
            val json = Gson().toJson(quizes!![0])
            intent.putExtra("QUIZ" , json)
            startActivity(intent)
            finish()
        }
    }

    private fun setUpFireStore() {

        val date = intent.getStringExtra("DATE")

        if(date!= null){
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("quizes").whereEqualTo("title" , date)
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty){
                        Log.d("DATA" , it.toObjects(Quiz::class.java).toString())
                        quizes = it.toObjects(Quiz::class.java)
                        questions  = quizes!![0].questions
                        bindViews()
                    }
                }
        }

    }

    private fun bindViews() {
        findViewById<Button>(R.id.btnPrev).visibility = View.GONE
        findViewById<Button>(R.id.btnNext).visibility = View.GONE
        findViewById<Button>(R.id.btnSubmit).visibility = View.GONE

        if(index == 1){  // first que
            findViewById<Button>(R.id.btnNext).visibility = View.VISIBLE
        }
        else if(index-1 == questions!!.size){
            findViewById<Button>(R.id.btnPrev).visibility = View.VISIBLE
            findViewById<Button>(R.id.btnSubmit).visibility = View.VISIBLE
        }

        else{
            findViewById<Button>(R.id.btnPrev).visibility = View.VISIBLE
            findViewById<Button>(R.id.btnNext).visibility = View.VISIBLE
        }

        val question = questions!!["Que.$index"]

        question?.let{

            val queDescrip = findViewById<TextView>(R.id.queDescription)
            queDescrip.text = it.description

            val optAdapter = optionAdapter(this , it)

            val optList = findViewById<RecyclerView>(R.id.optionList)

            optList.layoutManager = LinearLayoutManager(this)
            optList.adapter = optAdapter
            optList.setHasFixedSize(true)

        }


    }
}