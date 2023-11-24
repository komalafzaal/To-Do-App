package com.example.helloworldapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTask : AppCompatActivity() {

    private val taskDB: TodoDatabase by lazy{
        Room.databaseBuilder(this, TodoDatabase::class.java, "todoApp_DB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

//        val taskDB = TodoDatabase.getDatabase(applicationContext)

        var titleInput: EditText = findViewById(R.id.title_input)
        var detailsInput: EditText = findViewById(R.id.details_input)
        var addButton: Button = findViewById(R.id.add_btn)
        var backButton: ImageView = findViewById(R.id.back_button)


        addButton.setOnClickListener {
            val title = titleInput.text.toString()
            val details = detailsInput.text.toString()

            if (title.isNotBlank() && details.isNotBlank()) {
                taskDB.todoDao().insert(Todo(0, title, details, false))
                Toast.makeText(this@AddTask, "Added Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@AddTask, "Title and details must be provided", Toast.LENGTH_SHORT).show()
            }
        }


        backButton.setOnClickListener {
            finish()
        }
    }
}