package com.example.todoapp.Domain

import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Data.Repository.Repository

internal class DeleteOneUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteOne(id)
    }
}