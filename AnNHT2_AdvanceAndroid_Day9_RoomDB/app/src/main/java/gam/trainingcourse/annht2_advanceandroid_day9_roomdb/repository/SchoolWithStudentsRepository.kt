package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.DispatcherProvider
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.database.SchoolWithStudentsDao
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal.SchoolWithStudents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SchoolWithStudentsRepository @Inject constructor(
    private val schoolWithStudentsDao: SchoolWithStudentsDao,
    private val dispatcherProvider: DispatcherProvider
) : ISchoolWithStudentsRepository {

    override suspend fun insertSchool(school: School) {
        return withContext(dispatcherProvider.io){
            schoolWithStudentsDao.insertSchool(school)
        }
    }

    override suspend fun insertStudent(student: Student) {
        return withContext(dispatcherProvider.io){
            schoolWithStudentsDao.insertStudent(student)
        }
    }

    override fun getAllSchoolWithStudents(): Flow<List<SchoolWithStudents>> {
        return schoolWithStudentsDao.getAllSchoolStudentsReturnFlow().flowOn(dispatcherProvider.io)
    }

    override fun getAllSchools(): Flow<List<School>> {
        return schoolWithStudentsDao.getAllSchools().flowOn(dispatcherProvider.io)
    }

    override fun getCountAllSchools(): Flow<Int> {
        return schoolWithStudentsDao.getCountAllSchools().flowOn(dispatcherProvider.io)
    }

    override fun getCountAllStudents(): Flow<Int>  {
        return schoolWithStudentsDao.getCountAllStudents().flowOn(dispatcherProvider.io)
    }

    override suspend fun deleteSchool(school: School) {
        return withContext(dispatcherProvider.io){
            schoolWithStudentsDao.deleteSchool(school)
        }
    }

    override suspend fun deleteStudent(student: Student) {
        return withContext(dispatcherProvider.io){
            schoolWithStudentsDao.deleteStudent(student)
        }
    }

}