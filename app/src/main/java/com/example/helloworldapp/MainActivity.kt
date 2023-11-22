package com.example.helloworldapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null


    private lateinit var items: ArrayList<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private lateinit var lvItems: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo)
        val recyclerViewItem = findViewById<View>(R.id.recyclerview) as RecyclerView


//        lvItems = findViewById(R.id.listView)

        //------------------SIMPLE LISTVIEW-------------------

//        items = ArrayList()
//        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
//        lvItems.adapter = itemsAdapter
//
//        // Adding items
//        items.add("First Item")
//        items.add("Second Item")
//        items.add("Second Item")
//        items.add("Second Item")
//        items.add("Second Item")

        //-----------------CUSTOM LIST VIEW---------------
        //We need to mae a custom adapter for custom List view Display

//        val arrayList = ArrayList<Task>()
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//        arrayList.add(Task("Title 1", "Subtitle 1"))
//
//        val itemsAdapter = CustomAdapter(this, arrayList)
//        lvItems.adapter = itemsAdapter


        //----------------------RECYCLER VIEW-------------------
        layoutManager = LinearLayoutManager(this)
        recyclerViewItem.layoutManager = layoutManager
        adapter = RecyclerAdapter()

        recyclerViewItem.adapter = adapter
    }
}