package com.example.helloworldapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDAO {

    @Query("SELECT * from todo_table order by done desc")
    fun getAllTodos() : LiveData<List<Todo>>
    @Query("SELECT * from todo_table WHERE done = 'true' order by done desc")
    fun getCompletedTasks() : LiveData<List<Todo>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(todo : Todo)
    @Update
    fun update(todo: Todo)
    @Query("Update todo_table SET done = 'true' WHERE id = :id")
    fun updateCompletedStatus(id: Long)

    @Query("DELETE FROM todo_table")
    fun deleteAll()

    @Query("DELETE FROM todo_table WHERE id = :id")
    fun deleteItem(id: Long)

    @Query("SELECT * FROM todo_table WHERE id = :id")
    fun getTodoById(id: Long): Todo
}