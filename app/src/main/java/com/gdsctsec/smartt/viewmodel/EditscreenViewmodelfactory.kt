package com.gdsctsec.smartt.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditscreenViewmodelfactory(private var context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditScreenViewModel::class.java)){
            return EditScreenViewModel(context) as T
        }
        throw IllegalArgumentException("view model not found")
    }

}