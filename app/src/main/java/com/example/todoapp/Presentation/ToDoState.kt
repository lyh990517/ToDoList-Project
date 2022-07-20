package com.example.todoapp.Presentation

import com.example.todoapp.Data.Entity.ToDoEntity

sealed class ToDoState {
    object Uninitialized : ToDoState()

    object Loading : ToDoState()

    data class Success(
        val ToDoList: List<ToDoEntity>
    ) : ToDoState()

    data class SuccessDetail(
        val ToDoEntity: ToDoEntity
    ) : ToDoState()

    object Error : ToDoState()

    object Delete : ToDoState()

    object Insert : ToDoState()

    object Update : ToDoState()
}
