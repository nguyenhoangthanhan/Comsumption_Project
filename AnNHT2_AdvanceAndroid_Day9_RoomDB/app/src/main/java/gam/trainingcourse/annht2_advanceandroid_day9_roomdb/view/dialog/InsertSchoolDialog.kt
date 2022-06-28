package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.databinding.DialogAddSchoolBinding
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.School
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.base.BaseDialogFragment
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view_model.SchoolWithStudentsViewModel

class InsertSchoolDialog : BaseDialogFragment<DialogAddSchoolBinding>() {

    companion object{
        val TAG = InsertSchoolDialog::class.java.name ?: "InsertSchoolDialog"
    }

    private val viewModel: SchoolWithStudentsViewModel by activityViewModels()

    override fun createBiding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): DialogAddSchoolBinding = DialogAddSchoolBinding.inflate(inflater, container, false)

    override fun initEvent() {
        binding.btnOk.setOnClickListener {
            val newSchoolName = binding.edtSchoolName.text.toString()
            val newSchoolAddress = binding.edtSchoolAddress.text.toString()

            if (newSchoolName.isEmpty() || newSchoolAddress.isEmpty()){
                Toast.makeText(context, "Input mustn't empty", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.insertSchool(
                    School(
                        _school_name = newSchoolName,
                        _school_address = newSchoolName
                    )
                )
                dismiss()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}