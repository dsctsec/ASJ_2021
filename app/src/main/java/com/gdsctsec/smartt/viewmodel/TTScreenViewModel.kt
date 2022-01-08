package com.gdsctsec.smartt.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository

class TTScreenViewModel(val context: Context): ViewModel() {
    private var lecturesOfTheDayLiveList: LiveData<List<TimeTable>>
    private var lectureRepository: LectureRepository

    init {
        lectureRepository= LectureRepository(context,Weekday.Monday)
        lecturesOfTheDayLiveList=lectureRepository.getLecturesByWeekday(Weekday.Monday)
    }

    public fun getLiveLectures():LiveData<List<TimeTable>>{
        return lecturesOfTheDayLiveList
    }


}