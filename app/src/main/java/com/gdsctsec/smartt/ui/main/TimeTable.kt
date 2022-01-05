package com.gdsctsec.smartt.ui.main

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timeTable")
data class TimeTable(
    @PrimaryKey(autoGenerate = true)
    @NonNull var id: Int = 0,
    @NonNull var lec: String,
    @NonNull var startTime: Long,
    @NonNull var endTime: Long,
    @NonNull var weekday: Int
)

enum class Weekday {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
}
