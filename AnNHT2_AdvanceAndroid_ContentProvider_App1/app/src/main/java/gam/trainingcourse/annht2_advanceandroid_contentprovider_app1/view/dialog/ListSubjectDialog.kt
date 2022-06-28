package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view.dialog

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.R
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.databinding.ItemCheckboxBinding
import android.widget.ArrayAdapter
import android.widget.Button
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal.DayAndSubjects
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal.SubjectCheck
import java.security.SecureRandom
import java.util.*


object ListSubjectDialog {

    fun addListSubjectDialog(context: Context, iSendDayAndSubjects:ISendDayAndSubjects){

        var returnObjects = DayAndSubjects("","")

        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_item_list_subject)
        val recyclerView = dialog.findViewById<RecyclerView>(R.id.rvListSubjects)

        val arrSubjects:List<String> = context.resources.getStringArray(R.array.list_subjects).toList()

        val listSubjectCheck:MutableList<SubjectCheck> = mutableListOf()
        for (i in arrSubjects){
            listSubjectCheck.add(SubjectCheck(false, i))
        }
        val cbSubjectsAdapter = CbSubjectsAdapter(listSubjectCheck, context)
        recyclerView.adapter = cbSubjectsAdapter
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        val spinner = dialog.findViewById<Spinner>(R.id.spinnerDayChosen)
        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context,
            android.R.layout.simple_spinner_item,
            context.resources.getStringArray(R.array.day_in_weeks).toList()
        )
        spinner.adapter = spinnerAdapter

        dialog.findViewById<Button>(R.id.btnOk).setOnClickListener{
            returnObjects.currentDayInWeek = spinner.selectedItem.toString()
//            Log.d("stringListSubjects_currentDayInWeek", spinner.selectedItem.toString())

            var stringListSubjects = ""
            for (i in listSubjectCheck){
                if (i.checked){
                    stringListSubjects += i.subject+","
                }
            }
            Log.d("stringListSubjects", stringListSubjects)

            returnObjects.listSubjectThisDay = stringListSubjects
            iSendDayAndSubjects.sendDayAndSubjectsListener(returnObjects)
            dialog.dismiss()

//            Log.d("stringListSubjects_returnOnClickListener"
//                ,  returnObjects.currentDayInWeek+"___"+returnObjects.listSubjectThisDay)
        }
//        Log.d("stringListSubjects_returndismiss"
//            ,  returnObjects.currentDayInWeek+"___"+returnObjects.listSubjectThisDay)

        dialog.findViewById<Button>(R.id.btnCancel).setOnClickListener{
            dialog.dismiss()
        }

        dialog.show()
    }

    public class CbSubjectsAdapter : RecyclerView.Adapter<CbSubjectsAdapter.CbSubjectsViewHolder> {

        private var listSubjects: List<SubjectCheck>
        private var context:Context

        constructor(listSubjects: List<SubjectCheck>, context:Context){
            this@CbSubjectsAdapter.context = context
            this@CbSubjectsAdapter.listSubjects = listSubjects
        }

        class CbSubjectsViewHolder(private val binding: ItemCheckboxBinding)
            : RecyclerView.ViewHolder(binding.root) {
            fun onBind(subjectCheck: SubjectCheck){
                binding.cbSubject.text = subjectCheck.subject

                binding.cbSubject.setOnClickListener {
                    subjectCheck.checked = binding.cbSubject.isChecked
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CbSubjectsViewHolder {
            val binding = ItemCheckboxBinding.inflate(LayoutInflater.from(context), parent, false)
            return CbSubjectsViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CbSubjectsViewHolder, position: Int) {
            holder.onBind(listSubjects[position])
        }

        override fun getItemCount(): Int {
            return listSubjects.size
        }
    }

    interface ISendDayAndSubjects{
        fun sendDayAndSubjectsListener(dayAndSubjects:DayAndSubjects)
    }

}