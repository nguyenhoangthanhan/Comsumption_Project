package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository

import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.database.TimeTableDao
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeTableRepository @Inject constructor(
    private val timeTableDao: TimeTableDao,
    private val dispatcherProvider: DispatcherProvider
) : ITimeTableRepository{

    override suspend fun insertTimeTable(timeTable: TimeTable) {
        return withContext(dispatcherProvider.io){
            timeTableDao.insertAll(timeTable)
        }
    }

    override fun getAllTimeTables(): Flow<List<TimeTable>> {
        return timeTableDao.getAll().flowOn(dispatcherProvider.io)
    }
}