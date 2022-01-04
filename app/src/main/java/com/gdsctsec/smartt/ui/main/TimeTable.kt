package com.gdsctsec.smartt.ui.main

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timeTable")
data class TimeTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var lec: String,
    var startTime: Long,
    var endTime: Long,
    var weekday: Int
)

enum class Weekday {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
}
