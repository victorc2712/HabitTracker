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
    val HabitLD = MutableLiveData<ArrayList<Habit>>(arrayListOf())

    fun refresh() {

        val fileHelper = FileHelper(getApplication())
        val resultFromFile = fileHelper.readFromFile()
        val sType = object : TypeToken<ArrayList<Habit>>() {}.type

        val hasilListHabit: ArrayList<Habit> = Gson().fromJson(resultFromFile, sType)

        HabitLD.value = hasilListHabit
    }

    fun save() {
        val currentList = HabitLD.value
        val fileHelper = FileHelper(getApplication())
        val jsonString = Gson().toJson(currentList)

        fileHelper.writeToFile(jsonString)
    }

    fun addData( habitName: String,description: String, goal: Int, unit: String, icon: Int) {
        val currentList = HabitLD.value

        currentList.add(Habit(habitName, description, goal, unit,0, icon))

        HabitLD.value = currentList
        Log.d("ADD_DATA", currentList.toString())
        save()
    }
}