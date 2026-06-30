package com.nmp160423174.uts_anmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nmp160423174.uts_anmp.util.DB_NAME

@Database(entities = arrayOf(Habit::class), version = 1)
abstract class HabitDatabase: RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile private var instance: HabitDatabase ?= null
        private val LOCK = Any()

        fun BuildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HabitDatabase::class.java,
                DB_NAME
            ).build()

        operator fun invoke(context: Context) {
            if(instance == null) {
                synchronized(LOCK) {
                    instance ?: BuildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}