package com.gdsctsec.smartt.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Month
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class HomeScreenViewModel(private val context: Context) : ViewModel() {

    private var repository: LectureRepository
    private var lecturesOfTheDayLiveList: LiveData<List<TimeTable>>
    private val reqDayFormat: SimpleDateFormat
    private val dateOfTheDay: Date

    private lateinit var weekday: Weekday
    private lateinit var monthDate: String

    init {
        repository = LectureRepository(context, getWeekday())

        lecturesOfTheDayLiveList =
            repository.getLecturesByWeekday(getWeekday())

        reqDayFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
        dateOfTheDay = Date()
    }

    public fun getLiveLectureData(): LiveData<List<TimeTable>> {
        return lecturesOfTheDayLiveList
    }


    @RequiresApi(Build.VERSION_CODES.O)
    public fun getMonthDate(): String {
        monthDate = ""
        val reqDateFormat = SimpleDateFormat("dd")
        monthDate = reqDayFormat.format(dateOfTheDay) + ", " + reqDateFormat.format(dateOfTheDay)
        return monthDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun getWeekday(): Weekday {
        val reqDayFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
        val dayOfWeek = Date()
        weekday = Weekday.valueOf(reqDayFormat.format(dayOfWeek).toString())
        return weekday
    }


    public fun getWeekDayString(): String {
        var day = reqDayFormat.format(dateOfTheDay)
        return day
    }

    public fun removeLecture(lecture: TimeTable) {
        repository.deleteLecture(lecture.id)
    }

    public fun getHour(): Int {
        val dateObj = Date()
        val hour = (SimpleDateFormat("HH").format(dateObj)).toInt()
        return hour
    }

    public fun getMinute(): Int {
        val dataObj = Date()
        val minute = SimpleDateFormat("mm").format(dataObj).toInt()
        return minute
    }

}