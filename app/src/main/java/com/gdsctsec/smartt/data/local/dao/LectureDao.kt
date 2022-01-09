package com.gdsctsec.smartt.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.model.LectureCount

@Dao
interface LectureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLecture(lec: TimeTable)

    @Update
    suspend fun updateLecture(lec: TimeTable)

    @Query("SELECT * FROM timeTable WHERE weekday = :weekday")
    fun getLecturesByWeekday(weekday: Weekday = Weekday.Monday): LiveData<List<TimeTable>>

    @Query("DELETE FROM timeTable WHERE id = :id")
    suspend fun deleteLecture(id: Int)

    @Query("SELECT weekday as dow, count(lec) as lecNo FROM timeTable GROUP BY dow")
    fun getLectureCountPerWeekday(): LiveData<List<LectureCount>>
}