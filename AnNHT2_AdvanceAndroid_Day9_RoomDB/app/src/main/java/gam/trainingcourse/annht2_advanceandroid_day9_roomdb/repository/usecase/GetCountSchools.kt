package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.usecase

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.ISchoolWithStudentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountSchools @Inject constructor(
    private val iSchoolWithStudentsRepository: ISchoolWithStudentsRepository
) {
    fun getCountAllSchools(): Flow<Int> {
        return iSchoolWithStudentsRepository.getCountAllSchools()
    }
}