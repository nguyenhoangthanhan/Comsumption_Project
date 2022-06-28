package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal.DayInWeekAndSubjects
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal.InfoItemTimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.TimeTableRepository
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.usecase.GetTimeTableUseCase
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.repository.usecase.UpdateTimeTableUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeTableViewModel @Inject constructor(
    private val getTimeTableUseCase: GetTimeTableUseCase,
    private val updateTimeTableUseCase: UpdateTimeTableUseCase
): ViewModel(){

    private val _timeTable = MutableLiveData<List<TimeTable>>()
    val timeTable: LiveData<List<TimeTable>>
        get() = _timeTable

    fun insertTimeTable(timeTable: TimeTable){
        viewModelScope.launch {
            updateTimeTableUseCase.insertTimeTable(timeTable)
        }
    }

    fun getAllTimeTables(){
        viewModelScope.launch {
            getTimeTableUseCase.getAllTimeTable().catch {ex ->
                Log.e("Fresher_07_error", "getAllTimeTables() Exception : $ex")
                _timeTable.value = arrayListOf()
            }.collect { value ->
                _timeTable.value = value
            }
        }
    }
}
