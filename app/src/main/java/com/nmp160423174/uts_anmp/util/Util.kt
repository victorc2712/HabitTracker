package com.nmp160423174.uts_anmp.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nmp160423174.uts_anmp.model.HabitDatabase

val DB_NAME = "newhabitdb"

fun buildDb(context: Context): HabitDatabase {
    val db = HabitDatabase.BuildDatabase(context)
    return db
}