package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.usecase

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.ISchoolWithStudentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountStudents @Inject constructor(
    private val iSchoolWithStudentsRepository: ISchoolWithStudentsRepository
) {
    fun getCountAllStudents(): Flow<Int> {
        return iSchoolWithStudentsRepository.getCountAllStudents()
    }
}