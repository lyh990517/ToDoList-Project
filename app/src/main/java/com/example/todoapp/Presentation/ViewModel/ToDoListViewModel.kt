package com.example.todoapp.Presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Domain.DeleteOneUseCase
import com.example.todoapp.Domain.GetAllUseCase
import com.example.todoapp.Domain.InsertOneUseCase
import com.example.todoapp.Presentation.BaseViewModel
import com.example.todoapp.Presentation.ToDoState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ToDoListViewModel(
    private val getAllUseCase: GetAllUseCase,
    private val deleteOneUseCase: DeleteOneUseCase,
    private val insertOneUseCase: InsertOneUseCase
) : BaseViewModel() {

    private val _ToDoLiveData = MutableLiveData<ToDoState>(ToDoState.Uninitialized)
    val ToDoLiveData = _ToDoLiveData
    override fun fetchData(): Job = viewModelScope.launch {
        _ToDoLiveData.postValue(ToDoState.Loading)
        _ToDoLiveData.postValue(ToDoState.Success(getAllUseCase()))
    }

    fun deleteOne(id: Long) = viewModelScope.launch {
        _ToDoLiveData.postValue(ToDoState.Loading)
        deleteOneUseCase(id)
        _ToDoLiveData.postValue(ToDoState.Delete)
    }

    fun insertOne(entity: ToDoEntity) = viewModelScope.launch {
        _ToDoLiveData.postValue(ToDoState.Loading)
        insertOneUseCase(entity)
        _ToDoLiveData.postValue(ToDoState.Insert)
    }
}