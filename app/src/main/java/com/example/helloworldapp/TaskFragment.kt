package com.example.helloworldapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class TaskFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private val taskDB: TodoDatabase by lazy{
        Room.databaseBuilder(requireContext(), TodoDatabase::class.java, "todoApp_DB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        println("Inside OnCreateView")
        return inflater.inflate(R.layout.fragment_task, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("Inside OnViewCreated.")
        val recyclerViewItem = view.findViewById(R.id.recyclerview) as RecyclerView

        //----------------------RECYCLER VIEW-------------------
        layoutManager = LinearLayoutManager(view.context)
        recyclerViewItem.layoutManager = layoutManager
        adapter = RecyclerAdapter(true)

        recyclerViewItem.adapter = adapter

        val taskListLiveData = taskDB.todoDao().getAllTodos() // LiveData obtained from Room

        taskListLiveData.observe(viewLifecycleOwner) { taskList ->
            println("TaskList...${taskListLiveData.value?.size}")
            // Update the RecyclerView's dataset with the new list when LiveData changes
            (adapter as RecyclerAdapter).setTasks(taskList)
        }
    }

}