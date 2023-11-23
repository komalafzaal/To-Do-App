package com.example.helloworldapp
import android.content.Intent
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var titles = arrayOf(
        "title",
        "title",
        "title",
        "title",
        "title"
    )
    private var details = arrayOf(
        "detail",
        "detail",
        "detail",
        "detail",
        "detail"
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.title.text = titles[position]
        holder.subTitle.text = details[position]
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.title)
        var subTitle: TextView = itemView.findViewById(R.id.subtitle)

        var edit: ImageView = itemView.findViewById(R.id.edit_icon)


        //      Intents --> explicit intent on edit, delete and complete button
        init {
            edit.setOnClickListener {
                val editIntent = Intent(itemView.context, EditTask::class.java)
                itemView.context.startActivity(editIntent)
            }
        }
    }
}