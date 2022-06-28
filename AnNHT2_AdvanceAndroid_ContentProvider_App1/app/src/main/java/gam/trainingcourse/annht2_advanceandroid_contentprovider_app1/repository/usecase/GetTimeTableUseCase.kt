package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.usecase

import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.ITimeTableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTimeTableUseCase @Inject constructor(private val iTimeTableRepository: ITimeTableRepository){

    fun getAllTimeTable(): Flow<List<TimeTable>>{
        return iTimeTableRepository.getAllTimeTables()
    }

}