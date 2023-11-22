package com.example.helloworldapp
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class CustomAdapter(context: Context, private val taskList: ArrayList<Task>) :
    ArrayAdapter<Task>(context, 0, taskList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater= LayoutInflater.from(context)
        val view :View = inflater.inflate(R.layout.list_row, parent , false)


        val titleTextView : TextView = view.findViewById(R.id.title)
        val subtitleTextView : TextView = view.findViewById(R.id.subtitle)
        titleTextView.text = taskList[0].title
        subtitleTextView.text = taskList[1].subtitle
        return view
    }
}
