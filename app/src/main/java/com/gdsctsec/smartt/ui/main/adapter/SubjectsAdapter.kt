package com.gdsctsec.smartt.ui.main.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R

class SubjectsAdapter(
    val subjectList: MutableList<String>,
    val timeList: MutableList<String>,
    val isOverList: MutableList<Boolean>,
    private val listener: OnItemclicklistener
) :
    RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder>() {

    inner class SubjectViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview),
        View.OnClickListener {
        val subjectTextView: TextView = itemview.findViewById(R.id.home_lecture_name)
        val timeTextView: TextView = itemview.findViewById(R.id.home_lecture_timing)
        val logo: ImageView = itemview.findViewById(R.id.home_list_item_logo)

        init {
            itemview.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }
    }

    interface OnItemclicklistener {
        fun onItemClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_fragment_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {

        holder.subjectTextView.text = subjectList.get(position)
        holder.timeTextView.text = timeList.get(position)

        val activeColorList = mutableListOf<Int>(
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_pink
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_yellow
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_neon_blue
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_dark_blue
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_blue
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_red
            )

        )

        val passiveColorList = mutableListOf<Int>(
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_pink_alpha
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_yellow_alpha
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_neon_blue_alpha
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_dark_blue_alpha
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_blue_alpha
            ), ContextCompat.getColor(
                holder.itemView.context,
                R.color.faint_red_alpha
            )

        )

        if (isOverList.get(position) == false) {

            holder.timeTextView.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )

            holder.subjectTextView.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )

            holder.logo.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )

            holder.itemView.background.setTint(
                activeColorList.get(position % 6)
            )

        } else {

            holder.timeTextView.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.remindi_disbaled_icon
                )
            )

            holder.subjectTextView.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.remindi_disbaled_icon
                )
            )

            holder.logo.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.remindi_disbaled_icon
                )
            )

            holder.itemView.background.setTint(
                passiveColorList.get(position % 6)
            )

        }
    }
}
