package gam.trainingcourse.annht2_mock_final_fresherandroid_07.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.Constant

@Database(entities = [Budget::class], version = 4)
abstract class BudgetDatabase : RoomDatabase() {

    abstract fun budgetDao() : BudgetDao

    companion object{
        @Volatile
        private var INSTANCE : BudgetDatabase? = null

        fun getInstance(context: Context): BudgetDatabase = INSTANCE ?: synchronized(this){
            INSTANCE ?: Room.databaseBuilder(context.applicationContext,
            BudgetDatabase::class.java,
            Constant.DATABASE_NAME)
                .fallbackToDestructiveMigration().build()
                .also {
                    INSTANCE = it
                }
        }
    }
}