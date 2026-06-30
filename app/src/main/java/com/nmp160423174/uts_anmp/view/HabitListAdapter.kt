package com.nmp160423174.uts_anmp.view

import android.R
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nmp160423174.uts_anmp.databinding.HabitCardItemBinding
import com.nmp160423174.uts_anmp.model.Habit
import com.nmp160423174.uts_anmp.viewmodel.DetailHabitViewModel
import com.nmp160423174.uts_anmp.viewmodel.ListViewModel

class HabitListAdapter(val habitList: ArrayList<Habit>, private val viewModel: DetailHabitViewModel):
    RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>(), HabitCardListener{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding= HabitCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HabitViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {
        holder.binding.habit = habitList[position]
        holder.binding.listener = this

        if(holder.binding.habit.progress < holder.binding.habit.goal)
        {
            holder.binding.progressView.visibility = View.GONE
            holder.binding.chipComplete.visibility = View.GONE
            holder.binding.chipProgress.text= "In Progress"
            holder.binding.btnPlus.isEnabled=true
            holder.binding.chipProgress.chipBackgroundColor = ColorStateList.valueOf(Color.GRAY)
        }
        else
        {
            holder.binding.progressView.visibility = View.VISIBLE
            holder.binding.chipComplete.visibility = View.VISIBLE
            holder.binding.btnPlus.isEnabled=false
            holder.binding.chipProgress.text="Complete"
            holder.binding.chipProgress.chipBackgroundColor = ColorStateList.valueOf(Color.GREEN)
        }

        if (holder.binding.habit.progress==0)
        {
            holder.binding.btnMinus.isEnabled=false
        }
        else
        {
            holder.binding.btnMinus.isEnabled=true
        }

        holder.binding.txtHabit.setOnClickListener {
            val action = HabitListFragmentDirections.actionEditHabitFragment(holder.binding.habit.uuid)
            it.findNavController().navigate(action)
        }
    }

    fun updateHabitList(newHabitList: List<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = habitList.size

    override fun onPlusClick(
        view: View,
        habit: Habit
    ) {
        habit.progress++
        viewModel.UpdateProgress(habit.progress, habit.uuid)
        notifyDataSetChanged()
    }

    override fun onMinusClick(
        view: View,
        habit: Habit
    ) {
        habit.progress--
        viewModel.UpdateProgress(habit.progress, habit.uuid)
        notifyDataSetChanged()
    }

    class HabitViewHolder(var binding: HabitCardItemBinding)
        :RecyclerView.ViewHolder(binding.root)

}