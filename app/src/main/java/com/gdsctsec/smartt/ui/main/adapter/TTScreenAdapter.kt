package com.gdsctsec.smartt.ui.main.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.model.TTScreendata
import com.gdsctsec.smartt.ui.main.MainActivity
import com.gdsctsec.smartt.ui.main.TTSchedulingScreenFragmentDirections

class TTScreenAdapter(val context: Context, val list: List<TTScreendata>,private val listener: TTScreenAdapter.OnItemclicklistener) :
    RecyclerView.Adapter<TTScreenAdapter.TTScreenViewHolder>() {

    inner class TTScreenViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val weekdayTextView: TextView = itemView.findViewById(R.id.weekday_textview);
        val lecturesTextView: TextView = itemView.findViewById(R.id.lectures_textview);
        val card: CardView = itemView.findViewById(R.id.tt_scheduling_item_card_view)

        init{
           itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TTScreenViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.tt_scheduling_item, parent, false)



        return TTScreenViewHolder(view)
    }




    interface OnItemclicklistener{
        fun onItemClick(position: Int)
    }

    override fun onBindViewHolder(holder: TTScreenViewHolder, position: Int) {
        val data: TTScreendata = list[position]
        holder.weekdayTextView.text = context.resources.getString(data.weekDay)
        holder.lecturesTextView.text = data.lecturesNum.toString() + " lectures"
        holder.card.background.setTint(ContextCompat.getColor(context, data.colorId))

    }

    override fun getItemCount(): Int {
        return list.size
    }
}