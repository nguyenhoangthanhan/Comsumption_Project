package gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.model.InfoItemTimeTable

class TimeTableViewModel: ViewModel(){

    private val _infoItemTimeTable = MutableLiveData<List<InfoItemTimeTable>>()
    val listInfoItemTimeTable: LiveData<List<InfoItemTimeTable>>
        get() = _infoItemTimeTable

    fun insertTimeTable(listInfoItemTimeTable: List<InfoItemTimeTable>){
        _infoItemTimeTable.value = listInfoItemTimeTable
    }

    fun getAllTimeTables() : List<InfoItemTimeTable>? {
        return listInfoItemTimeTable.value
    }
}
