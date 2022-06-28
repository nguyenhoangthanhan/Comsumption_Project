package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view.fragment

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import dagger.hilt.android.AndroidEntryPoint
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.adapter.TimeTableAdapter
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.databinding.FragmentListTimetableBinding
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal.DayInWeekAndSubjects
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal.InfoItemTimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.timetable_provider.TimeTableProvider
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.Constant
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view.base.BaseFragment
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view_model.TimeTableViewModel

@AndroidEntryPoint
class ListTimeTableFragment : BaseFragment<FragmentListTimetableBinding>() {

    private val LOADER_TIME_TABLE = 1

    private val timeTableViewModel:TimeTableViewModel by activityViewModels()

    private val timeTableAdapter = TimeTableAdapter()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentListTimetableBinding = FragmentListTimetableBinding.inflate(inflater, container, false)

    override fun initAction() {
        binding.btnAddTimeTable.setOnClickListener{
            navigateAddNoteFragment()
        }
    }

    override fun observerLiveData() {
        timeTableViewModel.timeTable.observe(this){ timeTables ->
            if (!timeTables.isNullOrEmpty()){
                val listInfoItemTimeTable = setListForAdapter(timeTables)
                timeTableAdapter.submitList(listInfoItemTimeTable)
            }
        }
    }

    private fun setListForAdapter(timeTables: List<TimeTable>): MutableList<InfoItemTimeTable>{
        val listInfoItemTimeTable = mutableListOf<InfoItemTimeTable>()
        var infoItemTimeTable = InfoItemTimeTable(timeTables[0]._time_table_id
            , timeTables[0]._time_table_name, DayInWeekAndSubjects())
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
                infoItemTimeTable.dayInWeekAndSubjects.listSubjects =
                    mutableListOf("","","","","","","")
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

    override fun initData() {
        timeTableViewModel.getAllTimeTables()

        LoaderManager.getInstance(this).initLoader(LOADER_TIME_TABLE, null, loaderCallback)
    }

    override fun initView() {
        binding.rvTimeTable.setHasFixedSize(true)
        binding.rvTimeTable.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTimeTable.adapter = timeTableAdapter
    }

    private fun navigateAddNoteFragment() {
        val action = ListTimeTableFragmentDirections.actionListTimeTableFragmentToAddTimeTableFragment()
        findNavController().navigate(action)
    }


    private val loaderCallback = object : LoaderManager.LoaderCallbacks<Cursor> {
        override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
            val loader = CursorLoader(
                requireContext().applicationContext, TimeTableProvider.URI_TIME_TABLE,
                arrayOf(
                    Constant.TIMETABLE_ID, Constant.TIMETABLE_NAME,
                    Constant.DAY_IN_WEEK, Constant.SUBJECTS, "_pm_key",
                ), null, null, null
            )

            Log.d("cursor_data", loader.toString())
            return loader
        }

        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
//            adapter.setTimeTable(data)
        }

        override fun onLoaderReset(loader: Loader<Cursor>) {
//            adapter.setTimeTable(null)
        }

    }
}