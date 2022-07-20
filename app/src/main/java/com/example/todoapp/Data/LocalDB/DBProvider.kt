package com.example.todoapp.Data.LocalDB

import android.content.Context
import androidx.room.Room

internal fun provideDB(context: Context) =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideDao(database: ToDoDatabase) = database.Dao()