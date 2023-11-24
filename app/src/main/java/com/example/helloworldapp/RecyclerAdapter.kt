package com.example.helloworldapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class RecyclerAdapter (private val isAllTask: Boolean): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var tasks: List<Todo> = listOf()

    fun changeCompleteStatus(pos : Int, context: Context)
    {
        val taskDB: TodoDatabase by lazy{
            Room.databaseBuilder(context, TodoDatabase::class.java, "todoApp_DB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build()
        }
        taskDB.todoDao().updateCompletedStatus(tasks[pos].id)
        Toast.makeText(context, "Task Completed Successfully", Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()

    }
    fun deleteItem(pos : Int, context: Context)
    {
        val taskDB: TodoDatabase by lazy{
            Room.databaseBuilder(context, TodoDatabase::class.java, "todoApp_DB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build()
        }
        taskDB.todoDao().deleteItem(tasks[pos].id) // Delete from the database
        tasks = tasks.filterIndexed { index, _ -> index != pos } // Remove from the list
        notifyItemRemoved(pos) // Notify adapter about item removal
    }

    // Function to update the dataset and notify the adapter
    fun setTasks(taskList: List<Todo>) {
        tasks = taskList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val task = tasks[position]
        holder.title.text = task.title
        holder.subTitle.text = task.details

        if (isAllTask) {
            holder.itemView.findViewById<View>(R.id.edit_icon).visibility = View.VISIBLE
            holder.itemView.findViewById<View>(R.id.complete_icon).visibility = View.VISIBLE
            holder.itemView.findViewById<View>(R.id.delete_icon).visibility = View.VISIBLE

        } else {
            holder.itemView.findViewById<View>(R.id.edit_icon).visibility = View.GONE
            holder.itemView.findViewById<View>(R.id.complete_icon).visibility = View.GONE
            holder.itemView.findViewById<View>(R.id.delete_icon).visibility = View.GONE
        }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.title)
        var subTitle: TextView = itemView.findViewById(R.id.subtitle)

        var edit: ImageView = itemView.findViewById(R.id.edit_icon)
        var delete: ImageView = itemView.findViewById(R.id.delete_icon)
        var complete: ImageView = itemView.findViewById(R.id.complete_icon)



        // Intents --> explicit intent on edit, delete and complete button
        init {
            edit.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val todoId = tasks[position].id
                    val editIntent = Intent(itemView.context, EditTask::class.java)
                    editIntent.putExtra("taskId", todoId)
                    itemView.context.startActivity(editIntent)
                }
            }

            delete.setOnClickListener {
                val positionToDelete = adapterPosition
                if (positionToDelete != RecyclerView.NO_POSITION) {
                    deleteItem(positionToDelete, itemView.context)
                }
            }

            complete.setOnClickListener{
                val changeCompleteStatus = adapterPosition
                if (changeCompleteStatus != RecyclerView.NO_POSITION) {
                    changeCompleteStatus(changeCompleteStatus, itemView.context)
                }
            }
        }


    }
}