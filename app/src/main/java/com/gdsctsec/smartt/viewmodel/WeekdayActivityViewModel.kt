package com.gdsctsec.smartt.viewmodel

import android.content.Context
import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import java.lang.IllegalArgumentException
import java.util.*

class WeekdayActivityViewModel(val context: Context,val weekDay: String) : ViewModel() {
    private var lecturesOfTheDayLiveList: LiveData<List<TimeTable>>

    init{
        lecturesOfTheDayLiveList=LectureRepository(context,Weekday.valueOf(weekDay)).getLecturesByWeekday(Weekday.valueOf(weekDay))
    }

    fun getLiveLecturesData():LiveData<List<TimeTable>>{
        return lecturesOfTheDayLiveList
    }



}

class WeekdayActivityViewModelFactory(val context: Context,val weekDay: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeekdayActivityViewModel(context,weekDay) as T
    }

}
