package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.Constant

@Database(entities = [School::class, Student::class], version = 1)
abstract class SchoolWithStudentsDatabase : RoomDatabase() {
    abstract fun schoolWithStudentsDao(): SchoolWithStudentsDao

    companion object{
        @Volatile
        private var INSTANCE : SchoolWithStudentsDatabase? = null

        fun getInstance(context: Context): SchoolWithStudentsDatabase = INSTANCE ?: synchronized(this){
            INSTANCE ?: Room.databaseBuilder(context.applicationContext,
            SchoolWithStudentsDatabase::class.java,
            Constant.DATABASE_NAME).build()
                .also {
                    INSTANCE = it
                }
        }
    }
}