package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.database.TimeTableDao
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.ITimeTableRepository
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.TimeTableRepository
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.DispatcherProvider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideTimeTableRepository(timeTableDao: TimeTableDao, dispatcherProvider: DispatcherProvider)
    : ITimeTableRepository{
        return TimeTableRepository(timeTableDao, dispatcherProvider)
    }
}