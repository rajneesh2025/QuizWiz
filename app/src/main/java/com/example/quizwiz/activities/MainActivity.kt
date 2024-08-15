package com.example.quizwiz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizwiz.R
import com.example.quizwiz.adapters.QuizAdapter
import com.example.quizwiz.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import androidx.drawerlayout.widget.DrawerLayout

//import kotlinx.android.synthetic.main.activity_mainactivity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var firestore : FirebaseFirestore
    private lateinit var adapter : QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpViews()

    }


    private fun setUpViews(){
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecyclerView()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        val datePic = findViewById<FloatingActionButton>(R.id.btnDatePicker)
        datePic.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager , "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER" , datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))

                intent = Intent(this , question_activity::class.java)
                intent.putExtra("DATE" , date)
                                 Toast.makeText(this , date , Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener() {
                Log.d("DATEPICKER" , "choosen date cancelled")
            }
            datePicker.addOnCancelListener{
                Log.d("DATEPICKER" , "date picker canceled")
            }
        }
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionRefrence = firestore.collection("quizes")
        collectionRefrence.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this , "Error fetching data" , Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA" , value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRecyclerView() {
        val quizRecycler: RecyclerView = findViewById<RecyclerView>(R.id.quizRecyclerView)
        adapter = QuizAdapter(this , quizList)
        quizRecycler.layoutManager = GridLayoutManager(this , 2)
        quizRecycler.adapter = adapter
    }

    fun setUpDrawerLayout(){
        setSupportActionBar(findViewById(R.id.appBar))
        actionBarDrawerToggle = ActionBarDrawerToggle(this , findViewById(R.id.main) ,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()
        val navDrawer = findViewById<NavigationView>(R.id.navView)
        navDrawer.setNavigationItemSelectedListener { menuItem ->

            when(menuItem.itemId){
                R.id.btnProfile ->{
                    val intent = Intent(this , ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.btnAbout->{
                   intent = Intent(this , about_Activity::class.java)
                    startActivity(intent)
                }
                R.id.btnFollowUs ->{
                    Toast.makeText(this , "sorry! page not ready" , Toast.LENGTH_SHORT ).show()
                }
            }

            findViewById<DrawerLayout>(R.id.main).closeDrawers()
            true

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}