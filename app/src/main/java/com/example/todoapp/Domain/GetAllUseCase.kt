package com.example.todoapp.Domain

import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Data.Repository.Repository

internal class GetAllUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<ToDoEntity> {
        return repository.getAll()
    }
}