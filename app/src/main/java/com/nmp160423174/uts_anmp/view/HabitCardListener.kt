package com.nmp160423174.uts_anmp.view

import android.view.View
import com.nmp160423174.uts_anmp.model.Habit

interface HabitCardListener {
    fun onPlusClick(view: View, habit: Habit)
    fun onMinusClick(view: View, habit: Habit)
}