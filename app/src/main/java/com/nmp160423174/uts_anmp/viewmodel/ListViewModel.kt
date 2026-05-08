package com.nmp160423174.uts_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmp160423174.uts_anmp.model.Habit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp160423174.uts_anmp.util.FileHelper

class ListViewModel(application: Application): AndroidViewModel(application) {
    val HabitLD= MutableLiveData<ArrayList<Habit>>()
    fun refresh(){
//        if (HabitLD.value == null) {
//            HabitLD.value = arrayListOf(
//                Habit("rafa","Sidoarjo",9,6,0),
//                Habit("Victor","Mojo",9,0,0),
//                Habit("Vicidior","Malang",9,2,0),
//            )
//        }
        val sType = object: TypeToken<List<Habit>>(){}.type
        val filehelper = FileHelper(getApplication())
        val resultFromFile = filehelper.readFromFile()
        val hasilListHabit = Gson().fromJson<List<Habit>>(resultFromFile, sType)
        HabitLD.value = hasilListHabit as ArrayList<Habit>
        Log.d("print_file", hasilListHabit.toString())
    }

    fun save() {
        val currentList = HabitLD.value

        //simpan ke file
        val filehelper = FileHelper(getApplication())
        val jsonString = Gson().toJson(currentList)
        Log.d("print_file_write", jsonString)
        filehelper.writeToFile(jsonString)
    }

    fun addData(habitName:String, description:String, goal:Int, unit:Int, icon:Int) {
        val currentList = HabitLD.value

        currentList.add(Habit(habitName, description, goal,unit,icon))

        HabitLD.value = currentList
    }
}