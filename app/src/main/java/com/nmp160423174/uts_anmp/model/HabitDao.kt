package com.nmp160423174.uts_anmp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertAll(vararg habit: Habit)

    @Query("SELECT * FROM habit")
    fun SelectAllTodo():List<Habit>

    @Query("SELECT * FROM habit WHERE uuid= :id")
    fun SelectTodo(id:Int): Habit

    @Query("UPDATE habit SET name=:name, description=:description, goal=:goal, unit=:unit, iconResId=:iconResId WHERE uuid = :uuid")
    fun update(name:String, description:String, goal:Int, unit:String, iconResId:Int, uuid:Int)

    @Update
    fun updateTodo(habit: Habit)

    @Delete
    fun DeleteTodo(habit: Habit)
}
