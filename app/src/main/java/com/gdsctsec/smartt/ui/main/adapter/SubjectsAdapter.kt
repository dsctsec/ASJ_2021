package com.gdsctsec.smartt.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R

class SubjectsAdapter(val subjects: List<String>, val time: List<String>) :
    RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_fragment_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.subject.text = subjects[position]
        holder.time.text = subjects[position]
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    class SubjectViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val subject: TextView = view.findViewById(R.id.list_item_day_lecture)
        val time: TextView = view.findViewById(R.id.list_item_lecture_time)
    }
}