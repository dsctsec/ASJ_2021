package com.gdsctsec.smartt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TimeTable::class], version = 1, exportSchema = false)
abstract class LectureDatabase : RoomDatabase(){

    abstract fun lectureDao(): LectureDao

    companion object{
        @Volatile
        private var INSTANCE: LectureDatabase? = null

        fun getInstance(context: Context): LectureDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LectureDatabase::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
        }
    }
}
}