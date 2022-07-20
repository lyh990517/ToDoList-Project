package com.example.todoapp.Data.LocalDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.Data.Entity.ToDoEntity

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoEntity")
    suspend fun getAll(): List<ToDoEntity>

    @Insert
    suspend fun insertOne(entity: ToDoEntity)

    @Query("DELETE FROM ToDoEntity WHERE id = :id")
    suspend fun deleteOne(id: Long)

    @Query("SELECT * FROM ToDoEntity WHERE id =:id")
    suspend fun getOne(id: Long): ToDoEntity

    @Update
    suspend fun updateOne(entity: ToDoEntity)
}