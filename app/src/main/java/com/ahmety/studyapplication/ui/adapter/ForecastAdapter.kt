package com.ahmety.studyapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmety.studyapplication.R
import com.ahmety.studyapplication.databinding.LayoutHeaderBinding
import com.ahmety.studyapplication.databinding.RowForecastBinding
import com.ahmety.studyapplication.model.WeatherElement
import com.ahmety.studyapplication.utilities.loadImages
import com.ahmety.studyapplication.utilities.toFormatDate

class ForecastAdapter(
    private val cityName: String? = "",
    private val weatherPicUrl: String? = ""
) : ListAdapter<WeatherElement, RecyclerView.ViewHolder>(DiffCallback()) {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = LayoutHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = RowForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MViewHolder(binding, cityName, weatherPicUrl)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind()
            is MViewHolder -> holder.bind(getItem(position - 1))
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    class HeaderViewHolder(private val binding: LayoutHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
        }
    }

    class MViewHolder(
        private val binding: RowForecastBinding,
        private val cityName: String? = "",
        private val weatherPicUrl: String? = ""
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeatherElement) {
            binding.apply {
                ivIcon.loadImages(weatherPicUrl.toString())
                tvDate.text = item.date?.toFormatDate()
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
            return oldItem == newItem
        }
    }
}