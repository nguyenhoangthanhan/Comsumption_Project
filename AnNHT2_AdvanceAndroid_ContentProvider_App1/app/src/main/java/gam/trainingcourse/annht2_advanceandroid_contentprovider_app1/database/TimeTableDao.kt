package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.database

import android.database.Cursor
import androidx.room.*
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeTableDao {
    @Query("SELECT * FROM ${Constant.NAME_OF_TIMETABLE_TABLE}")
    fun getAll(): Flow<List<TimeTable>>

    @Query("SELECT * FROM ${Constant.NAME_OF_TIMETABLE_TABLE}")
    fun getAllReturnCursor(): Cursor

    @Query("SELECT * FROM  ${Constant.NAME_OF_TIMETABLE_TABLE}" +
            " WHERE  ${Constant.TIMETABLE_ID} IN (:listTimeTableIds)")
    fun loadAllByIds(listTimeTableIds: MutableList<String>): List<TimeTable>

    @Query("SELECT * FROM ${Constant.NAME_OF_TIMETABLE_TABLE}" +
            " WHERE ${Constant.TIMETABLE_ID} LIKE :idTimeTable_  LIMIT 1")
    fun findByIdTimeTable(idTimeTable_: String): TimeTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg timeTable: TimeTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFromContentProvider(timeTable: TimeTable) : Long

    @Delete
    fun delete(timeTable: TimeTable)

    @Query("DELETE FROM ${Constant.NAME_OF_TIMETABLE_TABLE} WHERE _pm_key = :id")
    fun deleteById(id: Long): Int

    @Update
    fun update(timeTable: TimeTable): Int
}