package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.usecase

import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.ISchoolWithStudentsRepository
import javax.inject.Inject

class UpdateStudentUseCase @Inject constructor(
    private val iSchoolWithStudentsRepository: ISchoolWithStudentsRepository){

    suspend fun insertStudent(student: Student){
        return iSchoolWithStudentsRepository.insertStudent(student)
    }
}