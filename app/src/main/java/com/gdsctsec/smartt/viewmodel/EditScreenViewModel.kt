package com.gdsctsec.smartt.viewmodel

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.TimePicker
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import com.gdsctsec.smartt.ui.edit.EditScreenFragment
import com.gdsctsec.smartt.ui.main.HomeScreenFragment
import com.gdsctsec.smartt.ui.main.lectureNew
import com.gdsctsec.smartt.ui.notifications.alarms.AlertReceiver
import java.util.*


class EditScreenViewModel(private val context: Context): ViewModel() {



    val repository = LectureRepository(context, Weekday.Monday)
    private lateinit var _c:Calendar
    val c:Calendar get() = _c
    private  var _random:Int=0
    val random:Int get() = _random

    fun addlecture(lecture : TimeTable){
        repository.addLecture(lecture)
        _random=(1..1000).random()
        lectureNew.put(random,lecture.lec)
        }
    fun updatelecture(lecture: TimeTable){
        repository.updateLecture(lecture)
    }
     fun timePick(v: TextView,startCode:Int) {
        val mTimePickerstart: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePickerstart = TimePickerDialog(context,
            R.style.MyTimePickerDialogTheme,
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    if (startCode==1) {
                        _c = Calendar.getInstance()
                        _c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        _c.set(Calendar.MINUTE, minute)
                    }
                    v.setText(onTimeSet(hourOfDay, minute))
                }
            },hour, minute, false)
        mTimePickerstart.show()

    }



    private fun onTimeSet(hourOfDay: Int, minute: Int): String {
        // Set a variable to hold the current time AM PM Status
        // Initially we set the variable value to AM


        var status = "am"
        if (hourOfDay > 11) {
            // If the hour is greater than or equal to 12
            // Then the current AM PM status is PM
            status = "pm"
        }

        // Initialize a new variable to hold 12 hour format hour value
        val hour_of_12_hour_format: Int
        hour_of_12_hour_format = if (hourOfDay > 12) {

            // If the hour is greater than or equal to 12
            // Then we subtract 12 from the hour to make it 12 hour format time
            hourOfDay - 12
        } else {
            hourOfDay
        }

        // Get the calling activity TextView reference

        // Display the 12 hour format time in app interface
        if (minute >= 0 && minute <= 9) {
            val time = "$hour_of_12_hour_format:0$minute $status"
            return time
        } else {
            val time = "$hour_of_12_hour_format:$minute $status"
            return time
        }

    }


}