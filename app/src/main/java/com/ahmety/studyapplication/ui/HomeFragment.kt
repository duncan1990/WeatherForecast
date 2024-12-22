package com.ahmety.studyapplication.ui

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmety.studyapplication.databinding.FragmentHomeBinding
import com.ahmety.studyapplication.ui.adapter.CitySelectionAdapter
import com.ahmety.studyapplication.utilities.customFilter
import com.ahmety.studyapplication.utilities.hideKeyboard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var cityNameArrayList = arrayListOf("Dublin", "London", "Barcelona", "New York")

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var adapter: CitySelectionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        initListeners()
    }

    private fun setAdapter() {
        adapter = CitySelectionAdapter(::onItemClick, ::onDeleteClick)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter?.submitList(cityNameArrayList)
    }

    private fun onDeleteClick(position: Int) {
        adapter?.notifyItemRemoved(position)
        cityNameArrayList.removeAt(position)
    }

    private fun onItemClick(cityName: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToWeatherForecastFragment(cityName)
        )
    }

    private fun initListeners() {
        binding.apply {
            btnAddCity.setOnClickListener {
                if (etAddCity.text.toString().isNotEmpty()){
                    cityNameArrayList.add(etAddCity.text.toString())
                    adapter?.notifyItemChanged(cityNameArrayList.lastIndex-1)
                    etAddCity.setText("")
                    hideKeyboard(etAddCity)
                    showAddedCityAnimation()
                } else {
                    hideKeyboard(etAddCity)
                    showErrorAddingCityAnimation()
                }
            }
            binding.etAddCity.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_SEND) {
                    btnAddCity.performClick()
                    true
                } else {
                    false
                }
            }
            etAddCity.filters = arrayOf(customFilter)
        }
    }

    private fun showAddedCityAnimation() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.layoutSuccessfullyAdded.root.visibility = View.VISIBLE
            binding.layoutSuccessfullyAdded.lottieAnimationView.playAnimation()
            delay(2000)
            binding.layoutSuccessfullyAdded.root.visibility = View.GONE
        }
    }

    private fun showErrorAddingCityAnimation() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.layoutErrorAdding.root.visibility = View.VISIBLE
            binding.layoutErrorAdding.lottieAnimationView.playAnimation()
            delay(2000)
            binding.layoutErrorAdding.root.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }


}