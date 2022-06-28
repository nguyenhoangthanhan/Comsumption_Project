package gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.utils.Constant
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.adapter.TimeTableAdapter
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.databinding.ActivityMainBinding
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.model.DayInWeekAndSubjects
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.model.InfoItemTimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app_getdata.model.TimeTable

class MainActivity : AppCompatActivity() {
    private var cursor: Cursor? = null

    private val timeTableAdapter = TimeTableAdapter()

    private var listTimeTable = mutableListOf<TimeTable>()
    private var listInfoItemTimeTable = mutableListOf<InfoItemTimeTable>()

    private var _binding : ActivityMainBinding? = null
    val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        initTimeTable()

        binding?.rvTimeTable?.adapter = timeTableAdapter
        timeTableAdapter.submitList(listInfoItemTimeTable)
        binding?.rvTimeTable?.layoutManager =
            WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    override fun onRestart() {
        initTimeTable()
        super.onRestart()
    }

    private fun initTimeTable() {
        listTimeTable.clear()
        listInfoItemTimeTable.clear()
        cursor = contentResolver.query(Constant.URI_SUBJECT, null, null, null, null)
        while (cursor!!.moveToNext()) {
            val _time_table_id = cursor?.getString(cursor!!.getColumnIndexOrThrow(Constant.TIMETABLE_ID))
            val _time_table_name = cursor?.getString(cursor!!.getColumnIndexOrThrow(Constant.TIMETABLE_NAME))
            val _day_in_week = cursor?.getString(cursor!!.getColumnIndexOrThrow(Constant.DAY_IN_WEEK))
            val _subjects = cursor?.getString(cursor!!.getColumnIndexOrThrow(Constant.SUBJECTS))
            val _pm_key = cursor?.getLong(cursor!!.getColumnIndexOrThrow("_pm_key"))
            if (_time_table_id != null && _time_table_name != null && _day_in_week != null &&
                _subjects != null && _pm_key != null){
                val timeTable = TimeTable(_time_table_id, _time_table_name, _day_in_week, _subjects, _pm_key)
                listTimeTable.add(timeTable)
            }
        }
        if (!listTimeTable.isNullOrEmpty()){
            listInfoItemTimeTable = setListForAdapter(listTimeTable)
            timeTableAdapter.submitList(listInfoItemTimeTable)
        }
    }

    private fun setListForAdapter(timeTables: List<TimeTable>): MutableList<InfoItemTimeTable>{
        val listInfoItemTimeTable = mutableListOf<InfoItemTimeTable>()
        var infoItemTimeTable = InfoItemTimeTable(timeTables[0]._time_table_id
            , timeTables[0]._time_table_name, DayInWeekAndSubjects()
        )
        var k = 0

        for (i in timeTables.indices){

            if (timeTables[i]._time_table_id == infoItemTimeTable.idTimeTable){
                addDataIntoInfoItemTimeTable(infoItemTimeTable, timeTables, i)
                if (i == timeTables.size - 1){
                    listInfoItemTimeTable.add(k++, infoItemTimeTable)
                }
            }
            else{
                listInfoItemTimeTable.add(k++, infoItemTimeTable)
                infoItemTimeTable = InfoItemTimeTable()

                infoItemTimeTable.dayInWeekAndSubjects.statusDayInWeek =
                    mutableListOf(false, false, false, false, false, false, false)
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects = mutableListOf("","","","","","","")
                infoItemTimeTable.idTimeTable = timeTables[i]._time_table_id
                infoItemTimeTable.nameTimeTable = timeTables[i]._time_table_name

                addDataIntoInfoItemTimeTable(infoItemTimeTable, timeTables, i)
            }
        }
        return listInfoItemTimeTable
    }

    private fun addDataIntoInfoItemTimeTable(
        infoItemTimeTable:InfoItemTimeTable, timeTables: List<TimeTable>, i: Int){

        when(timeTables[i]._day_in_week){
            Constant.MONDAY -> {
                infoItemTimeTable.dayInWeekAndSubjects.statusDayInWeek[0] = true
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects[0] = timeTables[i]._subjects
            }
            Constant.TUESDAY -> {
                infoItemTimeTable.dayInWeekAndSubjects.statusDayInWeek[1] = true
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects[1] = timeTables[i]._subjects
            }
            Constant.WEDNESDAY -> {
                infoItemTimeTable.dayInWeekAndSubjects.statusDayInWeek[2] = true
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects[2] = timeTables[i]._subjects
            }
            Constant.THURSDAY -> {
                infoItemTimeTable.dayInWeekAndSubjects.statusDayInWeek[3] = true
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects[3] = timeTables[i]._subjects
            }
            Constant.FRIDAY -> {
                infoItemTimeTable.dayInWeekAndSubjects.statusDayInWeek[4] = true
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects[4] = timeTables[i]._subjects
            }
            Constant.SATURDAY -> {
                infoItemTimeTable.dayInWeekAndSubjects.statusDayInWeek[5] = true
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects[5] = timeTables[i]._subjects
            }
            Constant.SUNDAY -> {
                infoItemTimeTable.dayInWeekAndSubjects.statusDayInWeek[6] = true
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects[6] = timeTables[i]._subjects
            }
        }
    }
}