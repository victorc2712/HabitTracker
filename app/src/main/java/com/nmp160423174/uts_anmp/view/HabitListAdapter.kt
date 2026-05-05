package com.nmp160423174.uts_anmp.view

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nmp160423174.uts_anmp.databinding.HabitCardItemBinding
import com.nmp160423174.uts_anmp.model.Habit

class HabitListAdapter(val habitList: ArrayList<Habit>): RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder{
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

//        holder.binding.btnDetail.setOnClickListener {
//            val student = habitList[position]
//            val action = HabitListFragmentDirections.actionStudentDetailFragment(student)
//            it.findNavController().navigate(action)
//        }
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