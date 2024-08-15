package com.example.quizwiz.activities

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizwiz.R
import com.example.quizwiz.models.Questions
import com.example.quizwiz.models.Quiz
import com.google.gson.Gson
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData  , Quiz::class.java)

        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for(entry in quiz.questions.entries){
            val question = entry.value
            builder.append("<font color='#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#399918'>Ans: ${question.answer}</font><br/><br/>")
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            findViewById<TextView>(R.id.txtAnswer).text = Html.fromHtml(builder.toString() , Html.FROM_HTML_MODE_COMPACT)
        }
        else{
            findViewById<TextView>(R.id.txtAnswer).text = Html.fromHtml(builder.toString())
        }

    }

    private fun calculateScore() {
        var score = 0
        for(entry in quiz.questions.entries){
            val question = entry.value
            if(question.userAns == question.answer){
                score += 10
            }
        }
        findViewById<TextView>(R.id.finalScore).text = score.toString()
    }
}