package com.gdsctsec.smartt.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import com.gdsctsec.smartt.model.LectureCount

class WeekdayActvityViewModel(val context: Context, private val weekday: String) : ViewModel() {
    private var lecturesOfTheDayLiveList: LiveData<List<TimeTable>>
    private lateinit var lectureCountList: LiveData<List<LectureCount>>
    private var lectureRepository: LectureRepository

    init {
        lectureRepository = LectureRepository(context, Weekday.valueOf(weekday))
        lectureCountList = lectureRepository.getLectureCountPerWeekday()
        lecturesOfTheDayLiveList = lectureRepository.getLecturesByWeekday(Weekday.valueOf(weekday))
    }

    public fun getLiveLecturesData(): LiveData<List<TimeTable>> {
        return lecturesOfTheDayLiveList
    }

    fun getLectureCountPerWeekday(): LiveData<List<LectureCount>> {
        return lectureCountList
    }
}