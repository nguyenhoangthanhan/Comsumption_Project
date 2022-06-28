package gam.trainingcourse.annht2_mock_final_fresherandroid_07.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.database.BudgetDao
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.database.BudgetDatabase
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.DispatcherProvider
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.StandardDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): BudgetDatabase
    = BudgetDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideBudgetDao(database: BudgetDatabase): BudgetDao
    = database.budgetDao()

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = StandardDispatcher()
}