package gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.usecase

import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.IBudgetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllBudgetUseCase @Inject constructor(
    private val iBudgetRepository: IBudgetRepository
) {
    fun getAllBudget(): Flow<List<Budget>> {
        return iBudgetRepository.getAllBudget()
    }


}