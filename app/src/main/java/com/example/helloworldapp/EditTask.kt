package com.example.helloworldapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.room.Room

class EditTask : AppCompatActivity() {
    private val taskDB: TodoDatabase by lazy{
        Room.databaseBuilder(this, TodoDatabase::class.java, "todoApp_DB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        var titleInput: EditText = findViewById(R.id.title_input)
        var detailsInput: EditText = findViewById(R.id.details_input)
        var updateButton: Button = findViewById(R.id.update_btn)
        var cancelButton: Button = findViewById(R.id.cancel_btn)
        var backButton: ImageView = findViewById(R.id.back_button)


        val taskId = intent.getLongExtra("taskId", 0)
        if (taskId != 0L) {
            val existingTask = taskDB.todoDao().getTodoById(taskId)
            titleInput.setText(existingTask.title)
            detailsInput.setText(existingTask.details)
        }

        updateButton.setOnClickListener {
            val title = titleInput.text.toString()
            val details = detailsInput.text.toString()

            if (title.isNotBlank() && details.isNotBlank()) {
                taskDB.todoDao().update(Todo(taskId, title, details, false))
                Toast.makeText(this@EditTask, "Updated Successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@EditTask, "Title and details must be provided", Toast.LENGTH_SHORT).show()
            }
        }

        cancelButton.setOnClickListener{
            finish()
        }
        backButton.setOnClickListener {
            finish()
        }
    }
}