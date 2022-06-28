package gam.trainingcourse.annht2_mock_final_fresherandroid_07.database

import androidx.room.*
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    @Transaction
    @Query("SELECT * FROM ${Constant.NAME_OF_BUDGET_TABLE}")
    fun getAllStoredBudget(): Flow<List<Budget>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBudget(budget: Budget)

    @Query("UPDATE ${Constant.NAME_OF_BUDGET_TABLE} SET ${Constant.BUDGET_SPENDING} = :spending WHERE ${Constant.BUDGET_ID} = :id")
    suspend fun updateSpendingBudgetById(spending: Long, id: Long)
}