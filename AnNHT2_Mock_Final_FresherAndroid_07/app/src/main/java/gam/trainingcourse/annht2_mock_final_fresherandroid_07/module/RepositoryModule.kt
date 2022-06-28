package gam.trainingcourse.annht2_mock_final_fresherandroid_07.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.database.BudgetDao
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.BudgetRepository
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.IBudgetRepository
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.DispatcherProvider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule{

    @Singleton
    @Provides
    fun provideBudgetRepository(budgetDao: BudgetDao
                                , dispatcherProvider: DispatcherProvider): IBudgetRepository{
        return BudgetRepository(budgetDao, dispatcherProvider)
    }
}