package com.gdsctsec.smartt.weekdayScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import javax.security.auth.Subject

class WeekdayAdapter(val time : List<String>, val subjects: List<String>) : RecyclerView.Adapter<WeekdayAdapter.WeekdayViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekdayViewHolder {
        return WeekdayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_weekday_rv, parent , false))
    }

    override fun onBindViewHolder(holder: WeekdayViewHolder, position: Int) {
        holder.timeLec.text = time[position]
        holder.subject.text = subjects[position]
    }

    override fun getItemCount() = time.size


    class WeekdayViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val timeLec : TextView = view.findViewById(R.id.time)
        val subject : TextView = view.findViewById(R.id.Subject)

    }

}
