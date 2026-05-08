package com.nmp160423174.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp160423174.uts_anmp.R
import com.nmp160423174.uts_anmp.databinding.FragmentHabitListBinding
import com.nmp160423174.uts_anmp.viewmodel.ListViewModel

class HabitListFragment : Fragment() {
    private lateinit var binding: FragmentHabitListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter : HabitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        adapter = HabitListAdapter(arrayListOf(), viewModel)

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = adapter

        observeViewModel()

        viewModel.refresh()

        binding.fabAdd.setOnClickListener {
            val action = HabitListFragmentDirections.actionCreateHabitFragment()
            it.findNavController().navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.HabitLD.observe(viewLifecycleOwner, Observer {
            adapter.updateHabitList(it)
        })
    }

}