package com.nmp160423174.uts_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @ColumnInfo(name="name")
    var name: String,
    @ColumnInfo(name="description")
    var description: String,
    @ColumnInfo(name="goal")
    var goal: Int,
    @ColumnInfo(name="unit")
    var unit: String,
    @ColumnInfo(name="progress")
    var progress: Int = 0,
    @ColumnInfo(name="iconResId")
    var iconResId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}