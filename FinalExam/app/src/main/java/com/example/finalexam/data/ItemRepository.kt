package com.example.finalexam.data

import kotlinx.coroutines.flow.Flow

class ItemRepository(private val dao: ItemDao) {
    fun getAllItems(): Flow<List<Item>> = dao.getAll()

    suspend fun getById(id: Long): Item? = dao.getById(id)

    suspend fun insert(item: Item): Long = dao.insert(item)

    suspend fun update(item: Item) = dao.update(item)

    suspend fun delete(item: Item) = dao.delete(item)
}

