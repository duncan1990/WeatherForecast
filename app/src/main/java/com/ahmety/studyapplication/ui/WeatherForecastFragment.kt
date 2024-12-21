package com.ahmety.studyapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ahmety.studyapplication.databinding.FragmentWeatherForecastBinding
import com.ahmety.studyapplication.model.WeatherResponse
import com.ahmety.studyapplication.viewmodel.WeatherForecastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherForecastFragment : Fragment() {

    private var _binding: FragmentWeatherForecastBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherForecastViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.loadWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        viewModel.weather.observe(viewLifecycleOwner) { weathers ->
            weathers
            //adapter?.submitList(articles)
        }
    }

    private fun setupViewModel() {
        viewModel.weather.observe(viewLifecycleOwner, renderWeather)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
        viewModel.isEmptyList.observe(viewLifecycleOwner, emptyListObserver)
    }

    private val renderWeather = Observer<WeatherResponse> {
        /*binding.layoutError.root.visibility = View.GONE
        binding.layoutEmpty.root.visibility = View.GONE
        adapter?.submitList(it)*/
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        //binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
    }

    private val onMessageErrorObserver = Observer<Any> {
        /*binding.layoutError.root.visibility = View.VISIBLE
        binding.layoutError.textViewError.text = getString(R.string.error_text, it)*/
    }

    private val emptyListObserver = Observer<Boolean> {
        //binding.layoutEmpty.root.visibility = if (it) View.VISIBLE else View.GONE
    }

    /*    private fun setupUI() {
            adapter = NewsAdapter { article ->
                findNavController().navigate(
                    NewsListFragmentDirections.actionNewsListFragmentToArticleDetailFragment(article)
                )
            }
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = adapter

            viewModel.news.observe(viewLifecycleOwner) { articles ->
                adapter?.submitList(articles)
            }

            setupSearch()
        }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}