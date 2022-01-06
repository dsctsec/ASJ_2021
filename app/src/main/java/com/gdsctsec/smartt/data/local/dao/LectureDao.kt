package com.gdsctsec.smartt.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday

@Dao
interface LectureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveLecture(lec: TimeTable)

    @Update
    suspend fun updateLecture(lec: TimeTable)

    @Query("SELECT * FROM timeTable WHERE weekday = :weekday")
    fun getLectureByWeekday(weekday: Weekday): LiveData<List<TimeTable>>

    @Query("DELETE FROM timeTable WHERE id = :id")
    suspend fun deleteLecture(id: Int)
}