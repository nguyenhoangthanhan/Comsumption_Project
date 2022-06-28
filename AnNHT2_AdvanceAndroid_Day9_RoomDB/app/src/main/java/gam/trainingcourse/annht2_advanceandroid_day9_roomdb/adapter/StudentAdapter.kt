package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.databinding.ItemStudentRvBinding
import gam.trainingcourse.annht2_advanceandroid_day9_roomdb.model.entity.Student

class StudentAdapter : ListAdapter<Student, StudentAdapter.StudentsViewHolder>(DIFF_UTILS)  {

    companion object {
        private var iDeleteStudent:IDeleteStudent? = null

        val DIFF_UTILS by lazy {
            object : DiffUtil.ItemCallback<Student>(){

                override fun areItemsTheSame(
                    oldItem: Student,
                    newItem: Student
                ): Boolean {
                    return oldItem.student_id == newItem.student_id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: Student,
                    newItem: Student
                ): Boolean {
                    return oldItem == newItem
                }

            }
        }
    }

    fun setInterfaceDeleteStudent(iDeleteStudent: IDeleteStudent){
        StudentAdapter.iDeleteStudent = iDeleteStudent
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentsViewHolder {
        val binding = ItemStudentRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class StudentsViewHolder(private val binding: ItemStudentRvBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun onBind(student: Student){
            binding.tvStudentName.text = student._student_name
            binding.tvStudentGrade.text = student._student_grade.toString()

            binding.imgDeleteStudent.setOnClickListener {
                iDeleteStudent?.deleteStudentListener(student)
            }
        }
    }
}