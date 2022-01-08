package com.gdsctsec.smartt.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.repository.LectureRepository
import com.gdsctsec.smartt.ui.edit.EditScreenActivity


class EditScreenViewModel(private val context: Context): ViewModel() {
    val repository = LectureRepository(context, Weekday.Monday)
    fun addlecture(lecture : TimeTable){
        repository.addLecture(lecture)
}
    fun updatelecture(lecture: TimeTable){
        repository.updateLecture(lecture)
    }
}