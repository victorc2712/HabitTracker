package com.nmp160423174.uts_anmp.model

data class Habit(
    val name: String,
    val description: String,
    val goal: Int,
    var progress: Int = 0,
    val iconResId: Int
)