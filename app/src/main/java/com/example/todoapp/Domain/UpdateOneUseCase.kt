package com.example.todoapp.Domain

import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Data.Repository.Repository

internal class UpdateOneUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(entity: ToDoEntity) {
        repository.getUpdate(entity)
    }
}