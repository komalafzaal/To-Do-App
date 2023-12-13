package com.example.helloworldapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


@Entity(tableName = "todo_table")
data class Todo(@PrimaryKey(autoGenerate = true) val id: Long = 0,
                @ColumnInfo(name = "title") val title: String,
                @ColumnInfo(name = "details") val details: String,
                @ColumnInfo(name = "done") val done: Boolean)