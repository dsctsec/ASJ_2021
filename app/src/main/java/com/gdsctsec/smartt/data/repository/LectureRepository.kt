package com.gdsctsec.smartt.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.gdsctsec.smartt.data.AppDatabase
import com.gdsctsec.smartt.data.TimeTable
import com.gdsctsec.smartt.data.Weekday
import com.gdsctsec.smartt.data.local.dao.LectureDao
import com.gdsctsec.smartt.util.CoroutineUtil

class LectureRepository(val context: Context, var weekday: Weekday) {

    private lateinit var db: AppDatabase
    private lateinit var lectureDao: LectureDao
    private lateinit var lectures: LiveData<List<TimeTable>>

    init {
        db = AppDatabase.getInstance(context.applicationContext)
        lectureDao = db.lectureDao()
        lectures = lectureDao.getLecturesByWeekday(weekday)
    }

    fun getLecturesByWeekday(weekday: Weekday): LiveData<List<TimeTable>> {
        return lectures
    }

    fun addLecture(lecture: TimeTable) {
        CoroutineUtil.io {
            lectureDao.addLecture(lecture)
        }
    }

    fun updateLecture(lecture: TimeTable) {
        CoroutineUtil.io {
            lectureDao.updateLecture(lecture)
        }
    }

    fun deleteLecture(id: Int) {
        CoroutineUtil.io {
            lectureDao.deleteLecture(id)
        }
    }
}