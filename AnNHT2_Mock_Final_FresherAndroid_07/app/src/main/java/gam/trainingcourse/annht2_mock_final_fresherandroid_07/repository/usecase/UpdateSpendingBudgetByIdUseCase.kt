package gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.usecase

import gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.IBudgetRepository
import javax.inject.Inject

class UpdateSpendingBudgetByIdUseCase @Inject constructor(
    private val iBudgetRepository: IBudgetRepository
) {
    suspend fun updateSpendingBudgetById(spending: Long, id: Long){
        iBudgetRepository.updateSpendingBudgetById(spending, id)
    }
}