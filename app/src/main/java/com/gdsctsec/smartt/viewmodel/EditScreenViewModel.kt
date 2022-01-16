package com.gdsctsec.smartt.viewmodel

import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.TimePicker
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsctsec.smartt.R
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*



private val _lectureNew:MutableMap<Int,String> = mutableMapOf()

class EditScreenViewModel(private val context: Context) : ViewModel() {
    private var _calendar:Calendar= Calendar.getInstance()

    val lectureNew:MutableMap<Int,String> get() = _lectureNew
    val calendar:Calendar get() = _calendar

    val repository = LectureRepository(context, Weekday.Monday)
    fun addlecture(lecture: TimeTable): MutableLiveData<Long> {
        val id = MutableLiveData<Long>()

        viewModelScope.launch {
            val lectureId = repository.addLecture(lecture)
            Log.e("ESVM",lectureId.toString())
            id.postValue(lectureId)
            lectureNew.put(lectureId.toInt(),lecture.lec)
        }

        return id
    }

    fun updatelecture(lecture: TimeTable) {
        repository.updateLecture(lecture)
        Log.e("updatedID",lecture.id.toString())
        lectureNew.put(lecture.id,lecture.lec)
    }

    fun timePick(v: TextView,startTime:Int) {
        val mTimePickerstart: TimePickerDialog
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)

        mTimePickerstart = TimePickerDialog(context,
            R.style.MyTimePickerDialogTheme,
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    v.setText(onTimeSet(hourOfDay, minute))
                    if (startTime==1) {
                        _calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        _calendar.set(Calendar.MINUTE, minute)
                    }
                }
            }, hour, minute, false
        )
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