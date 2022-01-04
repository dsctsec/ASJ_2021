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
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val timeList: List<String> = listOf(
            "10:00 - 12:00",
            "12:00 - 14:00",
            "14:00 - 16:00",
            "16:00 - 18:00",
            "08:00 - 10:00"
        )
        val subjectList: List<String> = listOf("Biology", "Math", "Java", "Science", "Python")
        recyclerView = view.findViewById(R.id.home_recyclerView)
        recyclerView.adapter = SubjectsAdapter(subjectList, timeList)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        super.onViewCreated(view, savedInstanceState)
    }
}