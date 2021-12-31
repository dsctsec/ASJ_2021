package com.gdsctsec.smartt.ui.main.weekday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.ui.main.weekday.adapter.WeekdayAdapter
import com.gdsctsec.smartt.viewmode.WeekdayActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WeekdayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekday)

        val imageViewWhenEmpty: ImageView = findViewById(R.id.empty_list_image_view)
        val lecturesRecyclerView: RecyclerView = findViewById(R.id.lecture_recycler_view)
        val lecNumberCountTextView: TextView = findViewById(R.id.lecture_number_text_view)
        val addNewLectureEventFloatingActionButton: FloatingActionButton = findViewById(R.id.lecture_add_floating_action_button)

        //recycler View Adapter
        val timeListRecyclerView: List<String> = listOf(
            "10:00 - 12:00",
            "12:00 - 14:00",
            "14:00 - 16:00",
            "16:00 - 18:00",
            "08:00 - 10:00"
        )
        val subjectListRecyclerView: List<String> =
            listOf("Biology", "Math", "Java", "Science", "Python")
        lecturesRecyclerView.adapter = WeekdayAdapter(timeListRecyclerView, subjectListRecyclerView)
        lecturesRecyclerView.layoutManager = LinearLayoutManager(this)

        lecNumberCountTextView.text = timeListRecyclerView.size.toString() + " Lectures"

        //Image visibility
        if (timeListRecyclerView.isNotEmpty()) imageViewWhenEmpty.visibility =
            View.GONE else imageViewWhenEmpty.visibility =
            View.GONE

        //ViewModel
        var viewModel = ViewModelProvider(this).get(WeekdayActivityViewModel::class.java)


        //Floating Button OnClick
        addNewLectureEventFloatingActionButton.setOnClickListener(View.OnClickListener {
            //Code to add a lec
        })
    }

    fun dayColor(day: Int) {
        val dayColorChangingToolbar: Toolbar = findViewById(R.id.weekday_activity_toolbar_top_card_view)
        val dayTextView: TextView = findViewById(R.id.day_text_view)

        val backgroundTintAwareDrawable = DrawableCompat.wrap(dayColorChangingToolbar.background)

        when (day) {
            1 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(this, R.color.color_monday)
                )
                dayTextView.text = "Monday"
            }
            2 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(this, R.color.color_tuesday)
                )
                dayTextView.text = "Tuesday"
            }
            3 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(this, R.color.color_wednesday)
                )
                dayTextView.text = "Wednesday"
            }
            4 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(this, R.color.color_thursday)
                )
                dayTextView.text = "Thursday"
            }
            5 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(this, R.color.color_friday)
                )
                dayTextView.text = "Friday"
            }
            6 -> {
                DrawableCompat.setTint(
                    backgroundTintAwareDrawable,
                    ContextCompat.getColor(this, R.color.color_saturday)
                )
                dayTextView.text = "Saturday"
            }
            else -> {
                Log.d("WeekdayActivity error", "unforeseen error occurred in when(x)")
            }
        }
    }
}
