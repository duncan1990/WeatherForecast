package com.ahmety.studyapplication.ui

import com.ahmety.studyapplication.viewmodel.WeatherForecastViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmety.studyapplication.databinding.FragmentWeatherForecastBinding
import com.ahmety.studyapplication.model.WeatherResponse
import com.ahmety.studyapplication.ui.adapter.ForecastAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastFragment : Fragment() {

    private var _binding: FragmentWeatherForecastBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherForecastViewModel by viewModels()
    private var adapter: ForecastAdapter? = null
    private val args: WeatherForecastFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        viewModel.loadWeather(args.cityName)
    }

    private fun setupViewModel() {
        // UIState gözlemi
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is WeatherForecastFragmentState.Loading -> showLoading()
                is WeatherForecastFragmentState.Success -> showWeatherData(state.data)
                is WeatherForecastFragmentState.Error -> showError(state.message)
                is WeatherForecastFragmentState.Empty -> showEmptyState()
            }
        }
    }

    private fun setupRecyclerView(data: WeatherResponse) {
        adapter = ForecastAdapter(cityName = args.cityName, weatherPicUrl = viewModel.weatherPicUrl)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter?.submitList(data.data?.weather)
    }

    private fun showLoading() {
        binding.layoutLoading.root.visibility = View.VISIBLE
    }

    private fun showWeatherData(data: WeatherResponse) {
        binding.layoutLoading.root.visibility = View.GONE
        setupRecyclerView(data)

    }

    private fun showError(message: String) {
         binding.layoutLoading.root.visibility = View.GONE
         binding.layoutError.root.visibility = View.VISIBLE
    }

    private fun showEmptyState() {
        binding.layoutLoading.root.visibility = View.GONE
        binding.layoutEmpty.root.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }
}
