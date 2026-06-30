package com.nmp160423174.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nmp160423174.uts_anmp.R
import com.nmp160423174.uts_anmp.databinding.FragmentCreateHabitBinding
import com.nmp160423174.uts_anmp.viewmodel.DetailHabitViewModel

class EditHabitFragment : Fragment() {
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

    private lateinit var binding: FragmentCreateHabitBinding
    private lateinit var viewModel: DetailHabitViewModel

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
        viewModel = ViewModelProvider(this).get(DetailHabitViewModel::class.java)
        setupSpinner()

        binding.txtHeader.text = "Habit Data"
        binding.btnCreateHabit.text = "Submit"

        val uuid = EditHabitFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        binding.btnCreateHabit.setOnClickListener {
            val selectedIcon = iconList[binding.comboIcon.selectedItemPosition]
            viewModel.Update(
                binding.habitName.text.toString(),
                binding.description.text.toString(),
                binding.goal.text.toString().toInt(),
                binding.unit.text.toString(),
                selectedIcon,
                uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.habitLD.observe(viewLifecycleOwner, Observer {
            binding.habit = it
        })
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, iconNameList)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.comboIcon.adapter = adapter
    }
}