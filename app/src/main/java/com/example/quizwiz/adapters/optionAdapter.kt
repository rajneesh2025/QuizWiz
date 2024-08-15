package com.example.quizwiz.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quizwiz.R
import com.example.quizwiz.models.Questions
import com.example.quizwiz.utils.colorPicker


class optionAdapter(val context: Context, val question: Questions ):
    RecyclerView.Adapter<optionAdapter.optionViewHolder>() {

    var options:List<String> = listOf(question.option1 , question.option2 , question.option3 , question.option4)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.option_item , parent , false)
        return optionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: optionViewHolder, position: Int) {
       holder.optionView.text = options[position]
        holder.itemView.setOnClickListener{
//            Toast.makeText(context , options[position] , Toast.LENGTH_SHORT).show()
            question.userAns = options[position]
            notifyDataSetChanged()
        }
        if(question.userAns == options[position]){
//            Toast.makeText(context , question.userAns , Toast.LENGTH_SHORT).show()
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }


    inner class optionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val optionView  = itemView.findViewById<TextView>(R.id.quiz_option)
    }
}
