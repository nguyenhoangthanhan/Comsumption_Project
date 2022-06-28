package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.databinding.ItemTimeTableBinding
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal.InfoItemTimeTable
import gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view_model.TimeTableViewModel
import java.sql.Time

class TimeTableAdapter : ListAdapter<InfoItemTimeTable, TimeTableAdapter.TimeTableViewHolder>(
    DIFF_UTILS) {

    companion object{
        val DIFF_UTILS by lazy {
            object : DiffUtil.ItemCallback<InfoItemTimeTable>() {
                override fun areItemsTheSame(
                    oldItem: InfoItemTimeTable,
                    newItem: InfoItemTimeTable
                ): Boolean {
                    return oldItem.idTimeTable == newItem.idTimeTable
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: InfoItemTimeTable,
                    newItem: InfoItemTimeTable
                ): Boolean {
                    return oldItem == newItem
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        val binding = ItemTimeTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeTableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class TimeTableViewHolder(private val binding: ItemTimeTableBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(infoItemTimeTable: InfoItemTimeTable){
            binding.infoItemTimeTable = infoItemTimeTable
        }
    }
}