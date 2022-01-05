package com.gdsctsec.smartt.ui.main

import androidx.room.*

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveLecture(lec: TimeTable)

    @Update
    suspend fun updateLecture(lec: TimeTable)

    @Query("SELECT * FROM timeTable WHERE weekday = :weekday")
    suspend fun getLectureByWeekday(weekday: Int): List<TimeTable>

    @Query("DELETE FROM timeTable WHERE id = :id")
    suspend fun deleteLecture(id: Int)
}