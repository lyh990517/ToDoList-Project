package com.example.todoapp.Data.LocalDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.Data.Entity.ToDoEntity

@Database(
    entities = [ToDoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun Dao(): ToDoDao

    companion object {
        const val DB_NAME = "ToDo.db"
    }
}