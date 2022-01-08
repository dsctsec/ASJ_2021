package com.gdsctsec.smartt.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class HomeScreenViewModelFactory(private var context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)){
            return HomeScreenViewModel(context) as T
        }
        throw IllegalArgumentException("view model not found")
    }

}