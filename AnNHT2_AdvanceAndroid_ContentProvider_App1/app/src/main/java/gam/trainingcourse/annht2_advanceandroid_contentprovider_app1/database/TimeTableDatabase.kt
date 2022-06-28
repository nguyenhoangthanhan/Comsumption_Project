package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.Constant

@Database(entities = [TimeTable::class], version = 1)
abstract class TimeTableDatabase : RoomDatabase() {
    abstract fun timeTableDao(): TimeTableDao

    companion object{
        @Volatile
        private var INSTANCE : TimeTableDatabase? = null

        fun getInstance(context: Context):TimeTableDatabase = INSTANCE ?: synchronized(this){
            INSTANCE?: Room.databaseBuilder(context.applicationContext,
            TimeTableDatabase::class.java,
            Constant.NAME_OF_TIMETABLE_TABLE).build()
                .also {
                    INSTANCE = it
                }
        }
    }
}