package com.example.todoapp.Data.Repository

import com.example.todoapp.Data.Entity.ToDoEntity

interface Repository {

    suspend fun getAll(): List<ToDoEntity>

    suspend fun insertOne(entity: ToDoEntity)

    suspend fun deleteOne(id: Long)

    suspend fun getOne(id: Long): ToDoEntity

    suspend fun getUpdate(entity: ToDoEntity)
}