package com.gdsctsec.smartt.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class WeekdayActivityViewModelFactory(val context: Context,private val weekday:String):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeekdayActvityViewModel::class.java)){
            return WeekdayActvityViewModel(context,weekday) as T
        }
        throw IllegalArgumentException("view model not found")
    }

}