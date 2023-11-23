package com.example.helloworldapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        var back: ImageView = findViewById(R.id.back_button)

        back.setOnClickListener {
            finish()
//            val backIntent = Intent(this.applicationContext, MainActivity::class.java)
//            startActivity(backIntent)
        }
    }
}