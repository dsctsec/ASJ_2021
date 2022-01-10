package com.gdsctsec.smartt.viewmodel

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TextView
import android.widget.TimePicker
import androidx.lifecycle.ViewModel
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import java.util.*


class EditScreenViewModel(private val context: Context): ViewModel() {
    val repository = LectureRepository(context, Weekday.Monday)
    fun addlecture(lecture : TimeTable){
        repository.addLecture(lecture)
}
    fun updatelecture(lecture: TimeTable){
        repository.updateLecture(lecture)
    }
     fun timePick(v: TextView) {
        val mTimePickerstart: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePickerstart = TimePickerDialog(context,
            R.style.MyTimePickerDialogTheme,
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    v.setText(onTimeSet(hourOfDay, minute))
                }
            }, hour, minute, false)
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