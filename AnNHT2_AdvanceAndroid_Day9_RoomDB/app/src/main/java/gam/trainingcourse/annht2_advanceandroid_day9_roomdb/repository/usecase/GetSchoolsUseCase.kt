package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.usecase

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.ISchoolWithStudentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSchoolsUseCase @Inject constructor(
    private val iSchoolWithStudentsRepository: ISchoolWithStudentsRepository
) {
    fun getAllSchools(): Flow<List<School>> {
        return iSchoolWithStudentsRepository.getAllSchools()
    }
}