package com.example.helloworldapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDAO
}
//@Database(entities = [Todo::class], version = 1, exportSchema = false)
//abstract class TodoDatabase : RoomDatabase() {
//    abstract fun todoDao(): TodoDAO
//    companion object {
//        @Volatile
//        private var INSTANCE: TodoDatabase? = null
//
//        fun getDatabase(context: Context): TodoDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TodoDatabase::class.java,
//                    "taskDB"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}

