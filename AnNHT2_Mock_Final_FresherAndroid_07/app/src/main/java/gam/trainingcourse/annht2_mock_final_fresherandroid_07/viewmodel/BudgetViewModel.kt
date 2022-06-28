package gam.trainingcourse.annht2_mock_final_fresherandroid_07.viewmodel

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.R
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.repository.usecase.*
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.Constant
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.LogUtils
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val getAllBudgetUseCase: GetAllBudgetUseCase,
    private val insertBudgetUseCase: InsertBudgetUseCase,
    private val updateSpendingBudgetByIdUseCase: UpdateSpendingBudgetByIdUseCase
) : ViewModel() {

    private val _budgets = MutableLiveData<List<Budget>>()
    val mBudget: LiveData<List<Budget>>
        get() =_budgets

    fun insertBudget(budget: Budget){
        viewModelScope.launch {
            insertBudgetUseCase.insertBudget(budget)
        }
    }

    fun getAllBudget(){
        viewModelScope.launch {
            getAllBudgetUseCase.getAllBudget().catch { ex->
                LogUtils.e("getAllBudget(): Exception : $ex")
            }.collect {budgets ->
                _budgets.value = budgets
            }
        }
    }

    fun initDataBudget(context: Context){
        insertBudget(
            Budget(
                budgetId = 1,
                budgetName = Constant.CAFE_TITLE,
                budgetConsuming = Constant.CAFE_BUDGET,
                unselectedImage = R.drawable.ic_restaurant_unselected,
                selectedImage = R.drawable.ic_restaurant,
                bgImg = R.drawable.bg_restaurant_item_img,
                ContextCompat.getColor(context, R.color.blue_3e39ec_item)
            )
        )

        insertBudget(
            Budget(
                budgetId = 2,
                budgetName = Constant.GROCERY_TITLE,
                budgetConsuming = Constant.GROCERY_BUDGET,
                unselectedImage = R.drawable.ic_grocery_unselected,
                selectedImage = R.drawable.ic_grocery,
                bgImg = R.drawable.bg_grocery_item_img,
                ContextCompat.getColor(context, R.color.green_33e7d0_item)
            )
        )

        insertBudget(
            Budget(
                budgetId = 3,
                budgetName = Constant.TAXI_TITLE,
                budgetConsuming = Constant.TAXI_BUDGET,
                unselectedImage = R.drawable.ic_car_wash_unselected,
                selectedImage = R.drawable.ic_car_wash,
                bgImg = R.drawable.bg_taxi_item_img,
                ContextCompat.getColor(context, R.color.orange_33e7d0_item)
            )
        )

        insertBudget(
            Budget(
                budgetId = 4,
                budgetName = Constant.GYM_TITLE,
                budgetConsuming = Constant.GYM_BUDGET,
                unselectedImage = R.drawable.ic_gym1_unselected,
                selectedImage = R.drawable.ic_gym1,
                bgImg = R.drawable.bg_gym_item_img,
                ContextCompat.getColor(context, R.color.purple_33e7d0_item)
            )
        )

        insertBudget(
            Budget(
                budgetId = 5,
                budgetName = Constant.MESSENGER_TITLE,
                budgetConsuming = Constant.MESSENGER_BUDGET,
                unselectedImage = R.drawable.ic_messenger_unselected,
                selectedImage = R.drawable.ic_messenger,
                bgImg = R.drawable.bg_messenger_item_img,
                ContextCompat.getColor(context, R.color.red_33e7d0_item)
            )
        )
    }

    fun updateSpendingBudgetById(spending: Long, id: Long){
        viewModelScope.launch {
            updateSpendingBudgetByIdUseCase.updateSpendingBudgetById(spending, id)
        }
    }

}