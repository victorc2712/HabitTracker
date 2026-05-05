package com.nmp160423174.uts_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmp160423174.uts_anmp.model.Habit

class ListViewModel: ViewModel() {
    val HabitLD= MutableLiveData<ArrayList<Habit>>()
    fun refresh(){
        HabitLD.value = arrayListOf(
            Habit("rafa","Sidoarjo",9,6,0),
            Habit("Victor","Mojo",9,0,0),
            Habit("Vicidior","Malang",9,2,0),
        )

    }
}