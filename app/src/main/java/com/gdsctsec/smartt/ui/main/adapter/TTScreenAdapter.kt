package com.gdsctsec.smartt.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.model.TTScreendata
import com.gdsctsec.smartt.ui.weekday.WeekdayActivity
import com.google.android.material.card.MaterialCardView

class TTScreenAdapter(val context: Context, val list: List<TTScreendata>) :
    RecyclerView.Adapter<TTScreenAdapter.TTScreenViewHolder>() {
    class TTScreenViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weekdayTextView: TextView = itemView.findViewById(R.id.weekday_textview);
        val lecturesTextView: TextView = itemView.findViewById(R.id.lectures_textview);
        val card: CardView = itemView.findViewById(R.id.tt_scheduling_item_card_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TTScreenViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.tt_scheduling_item, parent, false)
        return TTScreenViewHolder(view)
    }

    override fun onBindViewHolder(holder: TTScreenViewHolder, position: Int) {
        val data: TTScreendata = list[position]
        holder.weekdayTextView.text = context.resources.getString(data.weekDay)
        holder.lecturesTextView.text = context.resources.getString(data.lecturesNum)
        holder.card.background.setTint(ContextCompat.getColor(context, data.colorId))
        val weekNum=when(holder.weekdayTextView.text){
            "Monday"-> 1
            "Tuesday"->2
            "Wednesday"->3
            "Thursday"->4
            "Friday"->5
            "Saturday"->6
            else-> Log.i("Invalid","Invalid Weekday")
        }

        holder.card.setOnClickListener {
           val intent=Intent(context,WeekdayActivity::class.java).apply {
               putExtra("weekday",holder.weekdayTextView.text)
                putExtra("weeknum",weekNum.toString())
           }

           startActivity(context,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}