package com.nmp160423174.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nmp160423174.uts_anmp.R
import com.nmp160423174.uts_anmp.databinding.FragmentCreateHabitBinding
import com.nmp160423174.uts_anmp.viewmodel.ListViewModel

class CreateHabitFragment : Fragment() {

    private lateinit var binding: FragmentCreateHabitBinding
    private lateinit var viewModel: ListViewModel

    private val iconNameList = arrayOf(
        "Fitness",
        "Book",
        "Study",
        "Water"
    )

    private val iconList = arrayOf(
        R.drawable.baseline_fitness_center_24,
        R.drawable.baseline_menu_book_24,
        R.drawable.baseline_school_24,
        R.drawable.baseline_water_drop_24
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        setupSpinner()

        binding.btnCreateHabit.setOnClickListener {

            val habitName = binding.habitName.text.toString()
            val description = binding.description.text.toString()
            val goal = binding.goal.text.toString().toInt()
            val unit = binding.unit.text.toString().toInt()

            val selectedIcon = iconList[binding.comboIcon.selectedItemPosition]

            viewModel.addData(habitName, description, goal, unit, selectedIcon)
            viewModel.save()
        }
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, iconNameList)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.comboIcon.adapter = adapter
    }
}