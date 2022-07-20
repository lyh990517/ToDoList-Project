package com.example.todoapp.Presentation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Domain.DeleteOneUseCase
import com.example.todoapp.Domain.GetOneUseCase
import com.example.todoapp.Domain.UpdateOneUseCase
import com.example.todoapp.Presentation.BaseViewModel
import com.example.todoapp.Presentation.ToDoState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ToDoDetailViewModel(
    var id: Long = -1,
    private val getOneUseCase: GetOneUseCase,
    private val deleteOneUseCase: DeleteOneUseCase,
    private val updateOneUseCase: UpdateOneUseCase
) : BaseViewModel() {
    private val _ToDoDetailLiveData = MutableLiveData<ToDoState>(ToDoState.Uninitialized)
    val ToDoDetailLiveData = _ToDoDetailLiveData
    override fun fetchData(): Job = viewModelScope.launch {
        _ToDoDetailLiveData.postValue(ToDoState.Loading)
        _ToDoDetailLiveData.postValue(ToDoState.SuccessDetail(getOneUseCase(id)))
    }

    fun deleteItem(id: Long) = viewModelScope.launch {
        _ToDoDetailLiveData.postValue(ToDoState.Loading)
        deleteOneUseCase(id)
        _ToDoDetailLiveData.postValue(ToDoState.Delete)
    }

    fun updateItem(title: String, content: String) = viewModelScope.launch {
        _ToDoDetailLiveData.postValue(ToDoState.Loading)
        getOneUseCase(id)?.let {
            val updateEntity = it.copy(title = title, content = content)
            updateOneUseCase(updateEntity)
        }
        _ToDoDetailLiveData.postValue(ToDoState.Update)
    }
}