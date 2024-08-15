package com.example.quizwiz.adapters

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizwiz.models.About

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.quizwiz.R
import org.w3c.dom.Text

class aboutAdapter(var aboutItem : ArrayList<About> , var context : Activity) :
    RecyclerView.Adapter<aboutAdapter.AboutViewHolder>() {






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.about_each_item , parent , false)
        return AboutViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return aboutItem.size
    }

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        val currentItem = aboutItem[position]

        holder.abHeading.text = currentItem.heading
        holder.abDescr.text = currentItem.aboutDesc
        holder.abImg.setImageResource(currentItem.image)
    }


    class AboutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var abHeading = itemView.findViewById<TextView>(R.id.aboutHeading)
        var abImg = itemView.findViewById<ImageView>(R.id.aboutImg)
        var abDescr = itemView.findViewById<TextView>(R.id.aboutDescription)
    }
}