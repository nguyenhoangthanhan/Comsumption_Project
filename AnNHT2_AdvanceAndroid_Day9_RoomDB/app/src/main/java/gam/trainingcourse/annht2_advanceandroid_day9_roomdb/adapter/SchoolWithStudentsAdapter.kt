package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.databinding.ItemSchoolRvBinding
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.normal.SchoolWithStudents

class SchoolWithStudentsAdapter : ListAdapter<SchoolWithStudents
        , SchoolWithStudentsAdapter.SchoolWithStudentsViewHolder>(DIFF_UTILS) {

    companion object {
        private var studentsAdapter = StudentAdapter()
        private var iDeleteSchool:IDeleteSchool? = null

        val DIFF_UTILS by lazy {
            object : DiffUtil.ItemCallback<SchoolWithStudents>(){

                override fun areItemsTheSame(
                    oldItem: SchoolWithStudents,
                    newItem: SchoolWithStudents
                ): Boolean {
                    return oldItem.school.school_id == newItem.school.school_id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: SchoolWithStudents,
                    newItem: SchoolWithStudents
                ): Boolean {
                    return oldItem == newItem
                }

            }
        }
    }

    fun setInterfaceDeleteSchool(iDeleteSchool: IDeleteSchool){
        SchoolWithStudentsAdapter.iDeleteSchool = iDeleteSchool
    }

    fun setInterfaceDeleteStudent(iDeleteStudent: IDeleteStudent){
        studentsAdapter.setInterfaceDeleteStudent(iDeleteStudent)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SchoolWithStudentsViewHolder {
        val binding = ItemSchoolRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchoolWithStudentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolWithStudentsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class SchoolWithStudentsViewHolder(private val binding: ItemSchoolRvBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun onBind(schoolWithStudents: SchoolWithStudents){
            binding.schoolWithStudents = schoolWithStudents
            studentsAdapter = StudentAdapter()
            studentsAdapter.submitList(schoolWithStudents.listStudents)
            binding.rvSchoolWithStudents.adapter = studentsAdapter
            binding.rvSchoolWithStudents.layoutManager = LinearLayoutManager(binding.root.context)
            binding.imgShowStudentsOfSchool.setOnClickListener {
                if (binding.rvSchoolWithStudents.visibility == View.GONE){
                    binding.rvSchoolWithStudents.visibility = View.VISIBLE
                }
                else{
                    binding.rvSchoolWithStudents.visibility = View.GONE
                }
            }

            binding.imgDeleteSchool.setOnClickListener {
                iDeleteSchool?.deleteSchoolListener(schoolWithStudents)
            }
        }
    }
}