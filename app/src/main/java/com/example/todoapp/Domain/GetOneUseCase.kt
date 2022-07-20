package com.example.todoapp.Domain

import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Data.Repository.Repository

internal class GetOneUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Long): ToDoEntity {
        return repository.getOne(id)
    }
}