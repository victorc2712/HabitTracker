package com.nmp160423174.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp160423174.uts_anmp.R
import com.nmp160423174.uts_anmp.databinding.FragmentHabitListBinding
import com.nmp160423174.uts_anmp.viewmodel.ListViewModel

class HabitListFragment : Fragment() {

    private lateinit var binding: FragmentHabitListBinding
    private val adapter = HabitListAdapter(arrayListOf())
    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view model instantiate
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
        //viewModel.testSaveFile()

        //recycler view instantiate
        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = adapter
        observeViewModel()
//
//        //swipe refresh handle
//        binding.swipeRefresh.setOnRefreshListener {
//            viewModel.refresh()
//        }
    }

    fun observeViewModel() {
        //Observe studentsLD
        viewModel.HabitLD.observe(viewLifecycleOwner, Observer {
            adapter.updateHabitList(it)

        })
        //observe ErrorLD

    }

}