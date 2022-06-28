package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.DispatcherProvider
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.database.SchoolWithStudentsDao
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.ISchoolWithStudentsRepository
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.repository.SchoolWithStudentsRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideTimeTableRepository(schoolWithStudentsDao: SchoolWithStudentsDao
                                   , dispatcherProvider: DispatcherProvider)
    : ISchoolWithStudentsRepository{
        return SchoolWithStudentsRepository(schoolWithStudentsDao, dispatcherProvider)
    }
}