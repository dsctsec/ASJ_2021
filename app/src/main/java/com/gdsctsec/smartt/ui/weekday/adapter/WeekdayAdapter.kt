package com.gdsctsec.smartt.ui.weekday.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R

class WeekdayAdapter(val timeList: List<String>, val subjectList: List<String>) :
    RecyclerView.Adapter<WeekdayAdapter.WeekdayViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekdayViewHolder {
        return WeekdayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.lecture_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeekdayViewHolder, position: Int) {
        holder.timeLecTextView.text = timeList[position]
        holder.subjectTextView.text = subjectList[position]
    }

    override fun getItemCount() = timeList.size


    class WeekdayViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val timeLecTextView: TextView = view.findViewById(R.id.time)
        val subjectTextView: TextView = view.findViewById(R.id.subject)

    }

}
