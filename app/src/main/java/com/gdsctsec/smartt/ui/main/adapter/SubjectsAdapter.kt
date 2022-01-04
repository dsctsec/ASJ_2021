package com.gdsctsec.smartt.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R

class SubjectsAdapter(val subjects: List<String>, val time: List<String>) :
    RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val subjectTextView: TextView = view.findViewById(R.id.home_lecture_name)
        val timeTextView: TextView = view.findViewById(R.id.home_lecture_timing)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_fragment_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.subjectTextView.text = subjects[position]
        holder.timeTextView.text = time[position]

        when (position % 6) {
            0 -> {
                holder.itemView.background.setTint(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.faint_pink
                    )
                )
            }

            1 -> {
                holder.itemView.background.setTint(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.faint_yellow
                    )
                )
            }

            2 -> {
                holder.itemView.background.setTint(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.faint_neon_blue
                    )
                )
            }

            3 -> {
                holder.itemView.background.setTint(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.faint_dark_blue
                    )
                )
            }

            4 -> {
                holder.itemView.background.setTint(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.faint_blue
                    )
                )
            }

            5 -> {
                holder.itemView.background.setTint(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.faint_red
                    )
                )
            }
        }
    }
}
