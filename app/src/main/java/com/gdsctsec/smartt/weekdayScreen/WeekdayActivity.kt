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
        //test code delete it later
        Log.d("abcd", "chala")
        //getSupportActionBar().hide()
//        val ab : ActionBar? = supportActionBar
//        ab.hide()
//        val daycolor : Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(daycolor)


        val dayColor: Toolbar = findViewById(R.id.toolbar)
        val imageView: ImageView = findViewById(R.id.imageView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val dayTextView: TextView = findViewById(R.id.DayTextView)
        val lecNumber: TextView = findViewById(R.id.DayLecture)
        val changeDay: Button = findViewById(R.id.test)
        var addNewLec : FloatingActionButton = findViewById(R.id.AddLec)


        Toast.makeText(this, "asdas", Toast.LENGTH_SHORT).show()


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
        if (time.isNotEmpty()) {
            imageView.visibility = View.GONE
        } else {
            imageView.visibility = View.VISIBLE
        }

        //ViewModel

        var viewModel = ViewModelProvider(this).get(WeekdayActivityViewModel::class.java)
        viewModel.log()


        //TEST BUTTON VISIBLE
        changeDay.visibility = View.VISIBLE


        //Tint Change
        var backgroundD = dayColor.background
        backgroundD = DrawableCompat.wrap(backgroundD)


        //THIS IS A TEST BUTTON
        changeDay.setOnClickListener(View.OnClickListener {
            var randomday = (1..6).random()
            Toast.makeText(this, "Click $randomday", Toast.LENGTH_SHORT).show()
            var colorDay: List<Int> = listOf(
                R.color.monday,
                R.color.tuesday,
                R.color.wednesday,
                R.color.thursday,
                R.color.friday,
                R.color.saturday
            )
            //DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this,R.color.monday))
            //dayColor.background= backgroundD

            if (randomday == 1) {
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, colorDay[0]))
                dayTextView.text = "Monday"
            }
            if (randomday == 2) {
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, colorDay[1]))
                dayTextView.text = "Tuesday"

            }
            if (randomday == 3) {
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, colorDay[2]))
                dayTextView.text = "Wednesday"
            }
            if (randomday == 4) {
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, colorDay[3]))
                dayTextView.text = "Thursday"
            }
            if (randomday == 5) {
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, colorDay[4]))
                dayTextView.text = "Friday"
            }
            if (randomday == 6) {
                DrawableCompat.setTint(backgroundD, ContextCompat.getColor(this, colorDay[5]))
                dayTextView.text = "Saturday"
            }


        })

        //Test Button ENd


        //Floating Button OnClick
        addNewLec.setOnClickListener(View.OnClickListener {
            //Code to add a lec
        })

    }
}