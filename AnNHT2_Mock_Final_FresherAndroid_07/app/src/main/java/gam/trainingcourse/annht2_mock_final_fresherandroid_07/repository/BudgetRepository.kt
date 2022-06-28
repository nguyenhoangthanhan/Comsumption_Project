package gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository

import gam.trainingcourse.annht2_mock_final_fresherandroid_07.database.BudgetDao
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BudgetRepository @Inject constructor(
    private val budgetDao: BudgetDao,
    private val dispatcherProvider: DispatcherProvider
): IBudgetRepository{
    override suspend fun insertBudget(budget: Budget) {
        return withContext(dispatcherProvider.io){
            budgetDao.insertBudget(budget)
        }
    }

    override suspend fun updateSpendingBudgetById(spending: Long, id: Long) {
        return withContext(dispatcherProvider.io){
            budgetDao.updateSpendingBudgetById(spending, id)
        }
    }

    override fun getAllBudget(): Flow<List<Budget>> {
        return budgetDao.getAllStoredBudget().flowOn(dispatcherProvider.io)
    }

}