package com.nmp160423174.uts_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmp160423174.uts_anmp.model.Habit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp160423174.uts_anmp.model.HabitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val habitLD = MutableLiveData<List<Habit>>(arrayListOf())
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


    fun refresh() {
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = HabitDatabase.BuildDatabase(
                getApplication()
            )

            habitLD.postValue(db.habitDao().SelectAllHabit())
            loadingLD.postValue(false)
        }
    }
}