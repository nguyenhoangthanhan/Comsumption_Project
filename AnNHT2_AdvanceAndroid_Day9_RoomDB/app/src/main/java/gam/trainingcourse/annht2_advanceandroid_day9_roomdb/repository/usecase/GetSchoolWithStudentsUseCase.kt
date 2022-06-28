package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.usecase

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal.SchoolWithStudents
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.ISchoolWithStudentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSchoolWithStudentsUseCase @Inject constructor(
    private val iSchoolWithStudentsRepository: ISchoolWithStudentsRepository){

    fun getAllSchoolWithStudents(): Flow<List<SchoolWithStudents>>{
        return iSchoolWithStudentsRepository.getAllSchoolWithStudents()
    }

}