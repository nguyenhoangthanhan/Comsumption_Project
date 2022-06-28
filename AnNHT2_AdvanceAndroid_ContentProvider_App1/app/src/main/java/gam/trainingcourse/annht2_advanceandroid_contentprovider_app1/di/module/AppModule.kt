package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.database.TimeTableDao
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.database.TimeTableDatabase
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.DispatcherProvider
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.StandardDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): TimeTableDatabase = TimeTableDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideNoteDao(database: TimeTableDatabase): TimeTableDao = database.timeTableDao()

    @Singleton
    @Provides
    fun provideDispatcherProvider():DispatcherProvider = StandardDispatcher()
}