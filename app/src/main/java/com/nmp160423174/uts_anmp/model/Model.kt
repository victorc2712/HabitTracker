package com.nmp160423174.uts_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="description")
    val description: String,
    @ColumnInfo(name="goal")
    val goal: Int,
    @ColumnInfo(name="unit")
    val unit: String,
    @ColumnInfo(name="progress")
    var progress: Int = 0,
    @ColumnInfo(name="iconResId")
    val iconResId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}