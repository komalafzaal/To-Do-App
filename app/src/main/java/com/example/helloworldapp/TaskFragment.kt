package com.example.helloworldapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private val taskDB: TodoDatabase by lazy{
        Room.databaseBuilder(requireContext(), TodoDatabase::class.java, "todoApp_DB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerViewItem = view.findViewById(R.id.recyclerview) as RecyclerView

        //----------------------RECYCLER VIEW-------------------
        layoutManager = LinearLayoutManager(view.context)
        recyclerViewItem.layoutManager = layoutManager
        adapter = RecyclerAdapter(true)

        recyclerViewItem.adapter = adapter

//        val taskList = taskDB.todoDao().getAllTodos() // Fetch data from Room
//        (adapter as RecyclerAdapter).setTasks(taskList) // Set the fetched data to the adapter
//
        val taskListLiveData = taskDB.todoDao().getAllTodos() // LiveData obtained from Room

        taskListLiveData.observe(viewLifecycleOwner) { taskList ->
            // Update the RecyclerView's dataset with the new list when LiveData changes
            (adapter as RecyclerAdapter).setTasks(taskList)
        }
    }
}