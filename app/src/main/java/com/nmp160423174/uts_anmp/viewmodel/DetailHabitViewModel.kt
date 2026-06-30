package com.nmp160423174.uts_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nmp160423174.uts_anmp.model.Habit
import com.nmp160423174.uts_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailHabitViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val habitLD = MutableLiveData<Habit>()

    fun AddHabit(list:List<Habit>) {
        launch {
            val db = buildDb(getApplication())

            db.habitDao().InsertAll(*list.toTypedArray())
        }
    }

    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            habitLD.postValue(db.habitDao().SelectHabit(uuid))
        }
    }

    fun Update(name:String, description:String, goal:Int, unit:String, iconResId:Int, uuid:Int) {
        launch {
            val db =buildDb(getApplication())
            db.habitDao().update(name, description, goal, unit, iconResId, uuid)
        }
    }

    fun UpdateProgress(progress:Int, uuid:Int){
        launch {
            val db =buildDb(getApplication())
            db.habitDao().updateProgress(progress, uuid)
        }
    }

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

}