package gam.trainingcourse.annht2_mock_final_fresherandroid_07.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.alpha
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.databinding.ItemRulerBinding

class RulerPickerAdapter : RecyclerView.Adapter<RulerPickerAdapter.ViewHolder>() {

    private var binding: ItemRulerBinding? = null

    private val differUtil = object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return newItem.alpha == oldItem.alpha
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return newItem == oldItem
        }
    }

    var differ = AsyncListDiffer(this, differUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulerPickerAdapter.ViewHolder {
        binding = ItemRulerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RulerPickerAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: ItemRulerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            when {
                position % 4 == 0 -> {
                    binding.value1.visibility = View.GONE
                    binding.value2.visibility = View.VISIBLE
                }
                else -> {
                    binding.value1.visibility = View.VISIBLE
                    binding.value2.visibility = View.GONE
                }
            }
        }
    }
}