package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository

import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import kotlinx.coroutines.flow.Flow

interface ITimeTableRepository {
    suspend fun insertTimeTable(timeTable: TimeTable)

    fun getAllTimeTables(): Flow<List<TimeTable>>
}