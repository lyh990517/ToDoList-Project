package com.example.todoapp.Di

import com.example.todoapp.Data.LocalDB.provideDB
import com.example.todoapp.Data.LocalDB.provideDao
import com.example.todoapp.Data.Repository.Repository
import com.example.todoapp.Data.Repository.ToDoRepository
import com.example.todoapp.Domain.*
import com.example.todoapp.Domain.DeleteOneUseCase
import com.example.todoapp.Domain.GetAllUseCase
import com.example.todoapp.Domain.GetOneUseCase
import com.example.todoapp.Domain.InsertOneUseCase
import com.example.todoapp.Domain.UpdateOneUseCase
import com.example.todoapp.Presentation.ViewModel.ToDoDetailViewModel
import com.example.todoapp.Presentation.ViewModel.ToDoListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    //dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    //data
    single { provideDB(androidContext()) }
    single { provideDao(get()) }

    //repository
    single<Repository> { ToDoRepository(get(), get()) }

    //UseCase
    factory { GetAllUseCase(get()) }
    factory { DeleteOneUseCase(get()) }
    factory { InsertOneUseCase(get()) }
    factory { GetOneUseCase(get()) }
    factory { UpdateOneUseCase(get()) }

    //viewModel
    viewModel { (id: Long) -> ToDoDetailViewModel(id, get(), get(), get()) }
    viewModel { ToDoListViewModel(get(), get(), get()) }
}