package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.databinding.FragmentAddTimetableBinding
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.entity.TimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal.DayAndSubjects
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.utils.Constant
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view.base.BaseFragment
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view.dialog.ListSubjectDialog
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view_model.TimeTableViewModel

@AndroidEntryPoint
class AddTimeTableFragment : BaseFragment<FragmentAddTimetableBinding>(), ListSubjectDialog.ISendDayAndSubjects {

    private val timeTableViewModel: TimeTableViewModel by activityViewModels()

    private var listDayAndSubjects = mutableListOf<DayAndSubjects>()


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAddTimetableBinding = FragmentAddTimetableBinding.inflate(inflater, container, false)

    override fun initAction() {
        binding.btnAddDayInWeek.setOnClickListener {
            ListSubjectDialog.addListSubjectDialog(requireContext(), this)
        }

        binding.btnAddTimeTable.setOnClickListener{
            handleAddTimeTable()
            navigateToListTimeTable()
        }
    }

    private fun setTextViewDayInWeek(dayAndSubjects: DayAndSubjects){
        when (dayAndSubjects.currentDayInWeek){
            Constant.MONDAY -> {
                binding.tvListSubjectsMonday.text = dayAndSubjects.listSubjectThisDay
            }
            Constant.TUESDAY -> {
                binding.tvListSubjectsTuesday.text = dayAndSubjects.listSubjectThisDay
            }
            Constant.WEDNESDAY -> {
                binding.tvListSubjectsWednesday.text = dayAndSubjects.listSubjectThisDay
            }
            Constant.THURSDAY -> {
                binding.tvListSubjectsThursday.text = dayAndSubjects.listSubjectThisDay
            }
            Constant.FRIDAY -> {
                binding.tvListSubjectsFriday.text = dayAndSubjects.listSubjectThisDay
            }
            Constant.SATURDAY -> {
                binding.tvListSubjectsSaturday.text = dayAndSubjects.listSubjectThisDay
            }
            Constant.SUNDAY -> {
                binding.tvListSubjectsSunday.text = dayAndSubjects.listSubjectThisDay
            }
        }
    }

    private fun navigateToListTimeTable() {
        val action = AddTimeTableFragmentDirections.actionAddTimeTableFragmentToListTimeTableFragment()
        findNavController().navigate(action)
    }

    override fun sendDayAndSubjectsListener(dayAndSubjects:DayAndSubjects) {
        addDayInWeekIfItHaveYet(dayAndSubjects)
    }

    private fun addDayInWeekIfItHaveYet(dayAndSubjects: DayAndSubjects) {
        var checkDayHad = false
        for (i in listDayAndSubjects){
            if (i.currentDayInWeek == dayAndSubjects.currentDayInWeek){
                checkDayHad = true
                break
            }
        }
        if (!checkDayHad){
            listDayAndSubjects.add(dayAndSubjects)
            setTextViewDayInWeek(dayAndSubjects)
        }
    }

    private fun handleAddTimeTable() {
        for (i in listDayAndSubjects){
            val timeTable = TimeTable(
                _time_table_id = binding.edtInputNewId.text.toString(),
                _time_table_name = binding.edtInputNewName.text.toString(),
                _day_in_week = i.currentDayInWeek,
                _subjects = i.listSubjectThisDay
            )
            timeTableViewModel.insertTimeTable(timeTable)
        }
    }

}