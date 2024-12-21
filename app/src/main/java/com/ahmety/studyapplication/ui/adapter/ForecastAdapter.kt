package com.ahmety.studyapplication.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmety.studyapplication.R
import com.ahmety.studyapplication.databinding.RowCityBinding
import com.ahmety.studyapplication.databinding.RowForecastBinding
import com.ahmety.studyapplication.model.WeatherElement

class ForecastAdapter(
    private val cityName: String? = ""
) : ListAdapter<WeatherElement, ForecastAdapter.MViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val binding = RowForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MViewHolder(binding, cityName)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MViewHolder(
        private val binding: RowForecastBinding,
        private val cityName: String? = ""
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherElement) {
            binding.apply {
                tvDate.text = item.date
                tvCityName.text = cityName
                tvAvgDegree.text = tvAvgDegree.context.getString(R.string.temperature_with_symbol, item.avgtempC)
                tvMaxDegree.text = tvMaxDegree.context.getString(R.string.temperature_with_symbol, item.maxtempC)
                tvMinDegree.text = tvMinDegree.context.getString(R.string.temperature_with_symbol, item.mintempC)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<WeatherElement>() {
        override fun areItemsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean {
            return oldItem.date == newItem.date
        }
    }
}