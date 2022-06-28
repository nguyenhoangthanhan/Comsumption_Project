package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal.SchoolWithStudents
import kotlinx.coroutines.flow.Flow

interface ISchoolWithStudentsRepository {
    suspend fun insertSchool(school: School)

    suspend fun insertStudent(student: Student)

    fun getAllSchoolWithStudents(): Flow<List<SchoolWithStudents>>

    fun getAllSchools(): Flow<List<School>>

    fun getCountAllSchools(): Flow<Int>

    fun getCountAllStudents(): Flow<Int>

    suspend fun deleteSchool(school: School)

    suspend fun deleteStudent(student: Student)
}