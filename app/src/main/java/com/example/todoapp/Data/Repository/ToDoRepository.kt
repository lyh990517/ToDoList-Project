package com.example.todoapp.Data.Repository

import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Data.LocalDB.ToDoDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ToDoRepository(
    private val Dao: ToDoDao,
    private val IODispatcher: CoroutineDispatcher
) : Repository {
    override suspend fun getAll(): List<ToDoEntity> = withContext(IODispatcher) {
        Dao.getAll()
    }

    override suspend fun insertOne(entity: ToDoEntity) = withContext(IODispatcher) {
        Dao.insertOne(entity)
    }

    override suspend fun deleteOne(id: Long) = withContext(IODispatcher) {
        Dao.deleteOne(id)
    }

    override suspend fun getOne(id: Long): ToDoEntity = withContext(IODispatcher) {
        Dao.getOne(id)
    }

    override suspend fun getUpdate(entity: ToDoEntity) {
        Dao.updateOne(entity)
    }
}