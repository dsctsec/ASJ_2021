package com.gdsctsec.smartt.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import com.gdsctsec.smartt.model.LectureCount

class TTScreenViewModel(val context: Context) : ViewModel() {
    private var lecturesOfTheDayLiveList: LiveData<List<TimeTable>>
    private lateinit var lectureCountList: LiveData<List<LectureCount>>
    private var lectureRepository: LectureRepository

    init {
        lectureRepository = LectureRepository(context, Weekday.Monday)
        lectureCountList = lectureRepository.getLectureCountPerWeekday()
        lecturesOfTheDayLiveList = lectureRepository.getLecturesByWeekday(Weekday.Monday)
    }

    fun getLiveLectures(): LiveData<List<TimeTable>> {
        return lecturesOfTheDayLiveList
    }

    fun getLectureCountPerWeekday(): LiveData<List<LectureCount>> {
        return lectureCountList
    }
}