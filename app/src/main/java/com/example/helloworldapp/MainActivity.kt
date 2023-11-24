package com.example.helloworldapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
//
//
//    private lateinit var items: ArrayList<String>
//    private lateinit var itemsAdapter: ArrayAdapter<String>
//    private lateinit var lvItems: ListView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val database = TodoDatabase.getDatabase(this.applicationContext)
//        GlobalScope.launch {
//            database.todoDao().insert(Todo(0, "title", "details", true))
//        }

        loadFragment(TaskFragment())
        var bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.alltask -> {
                    loadFragment(TaskFragment())
                    true
                }

                R.id.completedtask -> {
                    loadFragment(CompletedTaskFragment())
                    true
                }

                else -> false
            }
        }

//        var circle: ImageView = findViewById(R.id.circle)
        var plus: ImageView = findViewById(R.id.fab)

        plus.setOnClickListener {
            val addIntent = Intent(this.applicationContext, AddTask::class.java)
            startActivity(addIntent)

        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }





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


}