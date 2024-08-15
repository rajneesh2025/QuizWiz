package com.example.quizwiz.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizwiz.R
import com.example.quizwiz.adapters.aboutAdapter
import com.example.quizwiz.models.About

class about_Activity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var aboutArrayList : ArrayList<About>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.aboutRecycler)

        val headingArray = arrayOf("Engaging Daily Quizzes" , "Personalized User Experience" , "Insights and Performance Tracking" ,
            "Community and Competitions" , "Educational Benefits")

        val imageArray = arrayOf(R.drawable.daily ,R.drawable.daily_quiz ,  R.drawable.permomance_overview ,
            R.drawable.community , R.drawable.educational_benefit)

        val descArray = arrayOf("Immerse yourself in a daily dose of knowledge with our dynamic quizzes featuring 8 to 10 stimulating questions. Each quiz covers a diverse range of topics, keeping your learning experience fresh and engaging. Whether you're a history buff or a science enthusiast, our carefully curated quizzes cater to every interest and challenge your intellect. Stay motivated and inspired as you expand your knowledge base one quiz at a time.",
            "Tailor your learning journey with personalized profiles and insightful analytics. Track your quiz performance effortlessly with detailed statistics and progress reports. Our app adapts to your learning style, offering recommendations and insights that help you identify strengths and areas for improvement. Unlock achievements and milestones as you progress, making learning a rewarding and personalized experience.",
            "Delve deep into your quiz results with comprehensive performance tracking. Analyze your scores over time, review question-level insights, and benchmark your performance against peers. Our detailed analytics empower you to set goals, measure progress, and refine your knowledge in specific subjects. Visualize your learning curve and celebrate your achievements as you master new topics and enhance your skills.",
            "Connect with a vibrant community of learners and quiz enthusiasts. Engage in friendly competitions, share quiz results, and collaborate with fellow users. Join discussions on trending topics and discover diverse perspectives from around the globe. Our community fosters a supportive environment where knowledge sharing and friendly competition thrive, making learning not only educational but also enjoyable and enriching.",
            "Elevate your learning experience with our educational quiz app. Gain practical knowledge, stay updated with current events, and develop critical thinking skills through interactive quizzes. Whether you're preparing for exams or simply expanding your horizons, our app provides a valuable resource for continuous learning. Embrace the benefits of lifelong education and cultivate a habit of intellectual curiosity with our intuitive and informative quiz platform."
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        aboutArrayList = arrayListOf<About>()
        for(index in imageArray.indices){
            var about = About(headingArray[index] , imageArray[index] , descArray[index])
            aboutArrayList.add(about)
        }

        val aboutadapter = aboutAdapter(aboutArrayList , this)
        recyclerView.adapter = aboutadapter

    }
}