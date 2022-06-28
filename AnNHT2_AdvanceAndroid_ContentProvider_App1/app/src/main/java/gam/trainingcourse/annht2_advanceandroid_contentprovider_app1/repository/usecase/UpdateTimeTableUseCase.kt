package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.usecase

import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.ITimeTableRepository
import javax.inject.Inject

class UpdateTimeTableUseCase @Inject constructor(private val iTimeTableRepository: ITimeTableRepository){

    suspend fun insertTimeTable(timeTable: TimeTable){
        return iTimeTableRepository.insertTimeTable(timeTable)
    }
}