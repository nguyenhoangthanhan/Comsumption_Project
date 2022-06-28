package gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository

import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import kotlinx.coroutines.flow.Flow

interface IBudgetRepository {

    suspend fun insertBudget(budget: Budget)

    suspend fun updateSpendingBudgetById(spending: Long, id: Long)

    fun getAllBudget(): Flow<List<Budget>>
}