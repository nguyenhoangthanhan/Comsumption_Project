package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.R
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.databinding.DialogAddStudentBinding
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.base.BaseDialogFragment
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view_model.SchoolWithStudentsViewModel
import java.lang.NumberFormatException

class InsertStudentDialog : BaseDialogFragment<DialogAddStudentBinding>() {

    companion object{
        val TAG = InsertStudentDialog::class.java.name ?: "InsertStudentDialog"
    }

    private val viewModel: SchoolWithStudentsViewModel by activityViewModels()
    private var idSchool:Long = -1

    override fun createBiding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): DialogAddStudentBinding = DialogAddStudentBinding.inflate(inflater, container, false)

    override fun initView() {
        val spinner = binding.spinnerSchoolChosen
        val listSchools: MutableList<School> = viewModel.mSchools.value as MutableList<School>?
            ?: return
        val adapter = context?.let { ArrayAdapter(it, R.layout.spinner_list_school, listSchools) }
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Get the value selected by the user
                // e.g. to store it as a field or immediately call a method
                val school: School = parent.selectedItem as School
                idSchool = school.school_id
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun initEvent() {
        binding.btnOk.setOnClickListener {
            val newStudentName = binding.edtStudentName.text.toString()
            val newStudentGrade = binding.edtStudentGrade.text.toString()

            if (newStudentName.isEmpty() || !isNumeric(newStudentGrade)){
                Toast.makeText(context, "Input mustn't empty", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.insertStudent(
                    Student(
                        school_id = idSchool,
                        _student_name = newStudentName,
                        _student_grade = newStudentGrade.toDouble().toInt()
                    )
                )
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun isNumeric(strNum: String): Boolean {
        try {
            strNum.toDouble().toInt()
        } catch (nfe: NumberFormatException) {
            return false
        }
        return true
    }
}