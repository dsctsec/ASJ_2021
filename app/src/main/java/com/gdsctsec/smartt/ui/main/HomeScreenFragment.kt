package com.gdsctsec.smartt.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.adapter.SubjectsAdapter

class HomeScreenFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binder = inflater.inflate(R.layout.fragment_home_screen, container, false)
        val time: List<String> = listOf("10:00 - 12:00", "12:00 - 14:00", "14:00 - 16:00", "16:00 - 18:00", "08:00 - 10:00")
        val subject: List<String> = listOf("Biology", "Math", "Java", "Science", "Python")
        recyclerView = binder.findViewById(R.id.fragment_home_recyclerView)
        recyclerView.adapter = SubjectsAdapter(subject,time)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return binder
    }

}