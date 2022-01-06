package com.gdsctsec.smartt.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LectureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveLecture(lec: TimeTable)

    @Update
    suspend fun updateLecture(lec: TimeTable)

    @Query("SELECT * FROM timeTable WHERE weekday = :weekday")
    suspend fun getLectureByWeekday(weekday: Weekday): LiveData<List<TimeTable>>

    @Query("DELETE FROM timeTable WHERE id = :id")
    suspend fun deleteLecture(id: Int)
}