package gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.usecase

import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.IBudgetRepository
import javax.inject.Inject

class InsertBudgetUseCase @Inject constructor(
    private val iBudgetRepository: IBudgetRepository
) {
    suspend fun insertBudget(budget: Budget){
        iBudgetRepository.insertBudget(budget)
    }
}