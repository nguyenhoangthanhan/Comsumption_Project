package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.adapter.IDeleteSchool
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.adapter.IDeleteStudent
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.adapter.SchoolWithStudentsAdapter
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.databinding.FragmentSchoolManagementBinding
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal.SchoolWithStudents
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.base.BaseFragment
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.dialog.InsertSchoolDialog
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.dialog.InsertStudentDialog
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view_model.SchoolWithStudentsViewModel

@AndroidEntryPoint
class SchoolManagementFragment : BaseFragment<FragmentSchoolManagementBinding>(),
    IDeleteStudent, IDeleteSchool {

    private val schoolWithStudentsViewModel: SchoolWithStudentsViewModel by activityViewModels()

    private val schoolWithStudentsAdapter = SchoolWithStudentsAdapter()

    override fun createBiding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSchoolManagementBinding =
        FragmentSchoolManagementBinding.inflate(inflater, container, false)

    override fun initView() {
        binding.rvListSchoolAndStudent.setHasFixedSize(true)
        binding.rvListSchoolAndStudent.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListSchoolAndStudent.adapter = schoolWithStudentsAdapter
    }

    override fun initData() {
        schoolWithStudentsViewModel.getAllSchoolWithStudents()
        schoolWithStudentsViewModel.getAllSchools()
        schoolWithStudentsViewModel.getSchoolsNumber()
        schoolWithStudentsViewModel.getStudentsNumber()
    }

    override fun observerLiveData() {
        schoolWithStudentsViewModel.mSchoolWithStudents.observe(this){ listSchoolWithStudents ->
            if (!listSchoolWithStudents.isNullOrEmpty()){
                schoolWithStudentsAdapter.submitList(listSchoolWithStudents)
                schoolWithStudentsAdapter.setInterfaceDeleteSchool(this)
                schoolWithStudentsAdapter.setInterfaceDeleteStudent(this)
            }
        }

        schoolWithStudentsViewModel.mCountSchools.observe(this){mCountSchools->
            Log.d("observerLiveData_mCountSchools",mCountSchools.toString())
            binding.tvSchoolNumber.text = mCountSchools.toString()
        }

        schoolWithStudentsViewModel.mCountStudents.observe(this){mCountStudents->
            Log.d("observerLiveData_mCountStudents",mCountStudents.toString())
            binding.tvStudentNumber.text = mCountStudents.toString()
        }
    }

    override fun initAction() {
        binding.btnAddSchool.setOnClickListener {
            InsertSchoolDialog().show(childFragmentManager, InsertSchoolDialog.TAG)
        }

        binding.btnAddStudent.setOnClickListener {
            InsertStudentDialog().show(childFragmentManager, InsertStudentDialog.TAG)
        }
    }

    override fun deleteSchoolListener(schoolWithStudents: SchoolWithStudents) {
        schoolWithStudentsViewModel.deleteSchool(schoolWithStudents.school)
        for (i in schoolWithStudents.listStudents){
            schoolWithStudentsViewModel.deleteStudent(i)
        }
    }

    override fun deleteStudentListener(student: Student) {
        schoolWithStudentsViewModel.deleteStudent(student)
    }

}