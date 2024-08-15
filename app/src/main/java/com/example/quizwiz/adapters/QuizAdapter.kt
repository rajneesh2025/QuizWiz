package com.example.quizwiz.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.quizwiz.R
import com.example.quizwiz.activities.question_activity
import com.example.quizwiz.models.Quiz
import com.example.quizwiz.utils.colorPicker
import com.example.quizwiz.utils.iconsPicker
import com.google.android.play.integrity.internal.s


class QuizAdapter(private val context: Context, private val quizes: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item , parent , false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.QuizTitle.text = quizes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorPicker.getColor()))
        holder.iconView.setImageResource(iconsPicker.getIcon())
        holder.itemView.setOnClickListener{
            Toast.makeText(context , quizes[position].title , Toast.LENGTH_SHORT).show()
            var intent = Intent(context , question_activity::class.java)
            intent.putExtra("DATE" , quizes[position].title)
            context.startActivity(intent)
        }
    }


    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var QuizTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}

