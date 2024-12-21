package com.ahmety.studyapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmety.studyapplication.databinding.FragmentHomeBinding
import com.ahmety.studyapplication.ui.adapter.CitySelectionAdapter
import com.ahmety.studyapplication.utilities.hideKeyboard

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
        /*findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToWeatherFragment(cityName)
        )*/
    }

    private fun initListeners() {
        binding.apply {
            btnAddCity.setOnClickListener {
                cityNameArrayList.add(etAddCity.text.toString())
                adapter?.notifyDataSetChanged()
                etAddCity.setText("")
                hideKeyboard(etAddCity)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }


}