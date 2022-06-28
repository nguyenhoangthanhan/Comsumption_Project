package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.usecase

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.ISchoolWithStudentsRepository
import javax.inject.Inject

class DeleteSchoolUseCase @Inject constructor(
    private val iSchoolWithStudentsRepository: ISchoolWithStudentsRepository
) {
    suspend fun deleteSchool(school:School) {
        return iSchoolWithStudentsRepository.deleteSchool(school)
    }
}