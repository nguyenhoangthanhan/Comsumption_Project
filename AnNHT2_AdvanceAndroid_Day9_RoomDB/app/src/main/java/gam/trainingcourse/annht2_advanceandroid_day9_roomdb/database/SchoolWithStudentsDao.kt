package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.database

import androidx.room.*
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal.SchoolWithStudents
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolWithStudentsDao {
    @Transaction
    @Query("SELECT * FROM ${Constant.NAME_OF_SCHOOL_TABLE}")
    fun getAllSchoolStudentsReturnFlow(): Flow<List<SchoolWithStudents>>

    @Query("SELECT * FROM ${Constant.NAME_OF_SCHOOL_TABLE}")
    fun getAllSchools(): Flow<List<School>>

    @Query("SELECT COUNT(${Constant.SCHOOL_ID}) FROM ${Constant.NAME_OF_SCHOOL_TABLE}")
    fun getCountAllSchools():Flow<Int>

    @Query("SELECT COUNT(${Constant.STUDENT_ID}) FROM ${Constant.NAME_OF_STUDENT_TABLE}")
    fun getCountAllStudents():Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Update
    suspend fun updateSchool(school: School)

    @Delete
    suspend fun deleteSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)
}