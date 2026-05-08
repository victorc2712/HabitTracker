package com.nmp160423174.uts_anmp.view

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nmp160423174.uts_anmp.databinding.HabitCardItemBinding
import com.nmp160423174.uts_anmp.model.Habit

class HabitListAdapter(val habitList: ArrayList<Habit>): RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
    val binding= HabitCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return HabitViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {

        holder.binding.txtHabit.text = habitList[position].name
        holder.binding.txtDesc.text = habitList[position].description
        holder.binding.txtProgress.text = habitList[position].progress.toString() + "/"+habitList[position].goal
        holder.binding.progressBar.progress= habitList[position].progress
        holder.binding.progressBar.max= habitList[position].goal

        if(habitList[position].progress < habitList[position].goal)
        {
            holder.binding.progressView.visibility = View.GONE
            holder.binding.chipComplete.visibility = View.GONE
            holder.binding.chipProgress.text= "In Progress"
            holder.binding.btnPlus.isEnabled=true
        }
        else
        {
            holder.binding.progressView.visibility = View.VISIBLE
            holder.binding.chipComplete.visibility = View.VISIBLE
            holder.binding.btnPlus.isEnabled=false
            holder.binding.chipProgress.text="Complete"
           // holder.binding.chipProgress.chipBackgroundColor

        }
        if (habitList[position].progress==0)
        {
            holder.binding.btnMinus.isEnabled=false
        }
        else
        {
            holder.binding.btnMinus.isEnabled=true
        }
        holder.binding.btnPlus.setOnClickListener {
            habitList[position].progress++
            notifyDataSetChanged()
        }
        holder.binding.btnMinus.setOnClickListener {
            habitList[position].progress--
            notifyDataSetChanged()
        }


    }


    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged() //menyuruh update adapternya
    }

    override fun getItemCount() = habitList.size

    class HabitViewHolder(var binding: HabitCardItemBinding)
        :RecyclerView.ViewHolder(binding.root)

}