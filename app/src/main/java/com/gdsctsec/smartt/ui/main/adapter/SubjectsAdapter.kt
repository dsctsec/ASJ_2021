package com.gdsctsec.smartt.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
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
        //val layt:ConstraintLayout = holder.itemView.findViewById(R.id.list_item)
        holder.subject.text = subjects[position]
        holder.time.text = subjects[position]
        //val border = DrawableCompat.wrap(layt.background)

        when (position % 6) {
            0 -> {
                holder.itemView.background.setTint(Color.parseColor("#33FD50C2"))
            }

            1 -> {
                holder.itemView.background.setTint(Color.parseColor("#33ECDA35"))
            }

            2 -> {
                holder.itemView.background.setTint(Color.parseColor("#3347EFE5"))
            }

            3 -> {
                holder.itemView.background.setTint(Color.parseColor("#333D27C6"))
            }

            4 -> {
                holder.itemView.background.setTint(Color.parseColor("#332694AC"))
            }

            5 -> {
                holder.itemView.background.setTint(Color.parseColor("#33DB2B2B"))
            }
        }
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    class SubjectViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val subject: TextView = view.findViewById(R.id.list_item_day_lecture)
        val time: TextView = view.findViewById(R.id.list_item_lecture_time)
    }
}