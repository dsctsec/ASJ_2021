package com.gdsctsec.smartt.weekdayScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeekdayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekday)

        val imageView: ImageView = findViewById(R.id.imageView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val lecNumber: TextView = findViewById(R.id.DayLecture)
        var addNewLec: FloatingActionButton = findViewById(R.id.AddLec)

        //recycler View Adapter
        val time: List<String> = listOf(
            "10:00 - 12:00",
            "12:00 - 14:00",
            "14:00 - 16:00",
            "16:00 - 18:00",
            "08:00 - 10:00"
        )
        val subject: List<String> = listOf("Biology", "Math", "Java", "Science", "Python")
        recyclerView.adapter = (WeekdayAdapter(time, subject))
        recyclerView.layoutManager = LinearLayoutManager(this)

        lecNumber.text = time.size.toString() + " Lectures"

        //Image visibility
        if (time.isNotEmpty()) imageView.visibility = View.GONE else imageView.visibility =
            View.GONE

        //ViewModel
        var viewModel = ViewModelProvider(this).get(WeekdayActivityViewModel::class.java)


        //Floating Button OnClick
        addNewLec.setOnClickListener(View.OnClickListener {
            //Code to add a lec
        })
    }

    fun dayColor(day: Int) {
        val dayColor: Toolbar = findViewById(R.id.toolbar)
        val dayTextView: TextView = findViewById(R.id.DayTextView)

        val backgroundD = DrawableCompat.wrap(dayColor.background)

        when (day) {
            1 -> {
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, R.color.monday))
                dayTextView.text = "Monday"
            }
            2->{
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, R.color.tuesday))
                dayTextView.text = "Tuesday"
            }
            3->{
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, R.color.wednesday))
                dayTextView.text = "Wednesday"
            }
            4->{
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, R.color.thursday))
                dayTextView.text = "Thursday"
            }
            5->{
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, R.color.friday))
                dayTextView.text = "Friday"
            }
            6->{
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, R.color.saturday))
                dayTextView.text = "Saturday"
            }
            else ->{
                    Log.d("WeekdayActivity error","unforeseen error occurred in when(x)")
            }
        }
    }
}
