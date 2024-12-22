package com.ahmety.studyapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmety.studyapplication.databinding.RowCityBinding

class CitySelectionAdapter(
    private val onItemClick: (String) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : ListAdapter<String, CitySelectionAdapter.MViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val binding = RowCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MViewHolder(binding, onItemClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MViewHolder(
        private val binding: RowCityBinding,
        private val onItemClick: (String) -> Unit,
        private val onDeleteClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.tvCityName.text = item

            binding.root.setOnClickListener {
                onItemClick(item)
            }

            binding.ivDelete.setOnClickListener {
                onDeleteClick(adapterPosition)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}