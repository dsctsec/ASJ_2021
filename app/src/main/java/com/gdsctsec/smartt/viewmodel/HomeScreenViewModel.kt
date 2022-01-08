package com.gdsctsec.smartt.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import java.util.*

class HomeScreenViewModel(private val context: Context) : ViewModel() {
    private lateinit var lectureRepository : LectureRepository
    private lateinit var lecturesOfTheDayLiveList: LiveData<List<TimeTable>>
    private lateinit var weekday: Weekday
    private lateinit var monthDate: String
 init {
     lecturesOfTheDayLiveList =
        lectureRepository.getLecturesByWeekday(getWeekday())
 }



    public fun getLiveLectureData(): LiveData<List<TimeTable>> {
        return lecturesOfTheDayLiveList
    }

    public fun getMonthDate(): String {
        monthDate=""
        when (Calendar.MONTH) {
            2 -> {
                monthDate = "January, " + (Calendar.DAY_OF_MONTH + 2)
            }
            3 -> {
                monthDate = "February, " + (Calendar.DAY_OF_MONTH + 2)
            }
            4 -> {
                monthDate = "March, " + (Calendar.DAY_OF_MONTH + 2)
            }
            5 -> {
                monthDate = "April, " + (Calendar.DAY_OF_MONTH + 2)
            }
            6 -> {
                monthDate = "May, " + (Calendar.DAY_OF_MONTH + 2)
            }
            7 -> {
                monthDate = "June, " + (Calendar.DAY_OF_MONTH + 2)
            }
            8 -> {
                monthDate = "July, " + (Calendar.DAY_OF_MONTH + 2)
            }
            9 -> {
                monthDate = "August, " + (Calendar.DAY_OF_MONTH + 2)
            }
            10 -> {
                monthDate = "September, " + (Calendar.DAY_OF_MONTH + 2)
            }
            11 -> {
                monthDate = "October, " + (Calendar.DAY_OF_MONTH + 2)
            }
            12 -> {
                monthDate = "November, " + (Calendar.DAY_OF_MONTH + 2)
            }
            1 -> {
                monthDate = "December, " + Calendar.DAY_OF_MONTH + 2
            }
        }
        return monthDate
    }

    public fun getWeekday(): Weekday {

        when (Calendar.DAY_OF_WEEK) {
            1 -> {
                weekday = Weekday.Monday
            }
            2 -> {
                weekday = Weekday.Monday
            }
            3 -> {
                weekday = Weekday.Tuesday
            }
            4 -> {
                weekday = Weekday.Wednesday
            }
            5 -> {
                weekday = Weekday.Thursday
            }
            6 -> {
                weekday = Weekday.Friday
            }
            7 -> {
                weekday = Weekday.Saturday
            }
        }
        return weekday
    }
    public fun getWeekDayString(): String {
        var day = ""
        when (Calendar.DAY_OF_WEEK) {
            1 -> {
                day = "Monday"
            }
            2 -> {
                day = "Tuesday"
            }
            3 -> {
                day = "Wednesday"
            }
            4 -> {
                day = "Thursday"
            }
            5 -> {
                day = "Friday"
            }
            6 -> {
                day = "Saturday"
            }
            7 -> {
                day = "Sunday"
            }
        }
        return day
    }
}