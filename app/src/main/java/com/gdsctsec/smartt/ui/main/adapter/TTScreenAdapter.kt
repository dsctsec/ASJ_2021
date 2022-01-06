package com.gdsctsec.smartt.ui.main.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.model.TTScreendata
import com.google.android.material.card.MaterialCardView

class TTScreenAdapter(val context: Context,val list:List<TTScreendata>): RecyclerView.Adapter<TTScreenAdapter.TTScreenViewHolder>() {
    class TTScreenViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
            val weekdayText:TextView=itemView.findViewById(R.id.weekday_textview);
            val lectures:TextView=itemView.findViewById(R.id.lectures_textview);
            val card:CardView=itemView.findViewById(R.id.card_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TTScreenViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.tt_scheduling_item,parent,false)
        return TTScreenViewHolder(view)
    }

    override fun onBindViewHolder(holder: TTScreenViewHolder, position: Int) {
        val data:TTScreendata=list[position]
        holder.weekdayText.text= context.resources.getString(data.weekDay)
        holder.lectures.text=context.resources.getString(data.lecturesNum)
        holder.card.background.setTint(ContextCompat.getColor(context,data.colorId))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}