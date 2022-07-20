package com.example.todoapp.Domain

import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Data.Repository.Repository

internal class InsertOneUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(entity: ToDoEntity) {
        repository.insertOne(entity)
    }
}