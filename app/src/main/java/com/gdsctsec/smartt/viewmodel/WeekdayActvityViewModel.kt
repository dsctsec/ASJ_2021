package com.gdsctsec.smartt.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository

class WeekdayActvityViewModel(val context: Context,private val weekday: String):ViewModel() {
    private lateinit var lecturesOfTheDayLiveList: LiveData<List<TimeTable>>

    init {
        lecturesOfTheDayLiveList=LectureRepository(context,Weekday.valueOf(weekday)).getLecturesByWeekday(
            Weekday.valueOf(weekday))
    }

    public fun getLiveLecturesData():LiveData<List<TimeTable>>{
        return lecturesOfTheDayLiveList
    }
}