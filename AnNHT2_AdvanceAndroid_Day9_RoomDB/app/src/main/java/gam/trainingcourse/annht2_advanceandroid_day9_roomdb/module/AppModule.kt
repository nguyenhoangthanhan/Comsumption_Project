package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.DispatcherProvider
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.database.SchoolWithStudentsDao
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.database.SchoolWithStudentsDatabase
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.utils.StandardDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): SchoolWithStudentsDatabase
    = SchoolWithStudentsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideSchoolWithStudentsDao(database: SchoolWithStudentsDatabase): SchoolWithStudentsDao
    = database.schoolWithStudentsDao()

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = StandardDispatcher()
}