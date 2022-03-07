package com.asthiseta.core.domain.repository

import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.Item
import kotlinx.coroutines.flow.*


interface IItemRepository {
    fun getAllKos(query: String?) : Flow<Resource<List<Item>>>

    fun getDetailKos(name : String): Flow<Resource<Item>>

    fun getFavoriteKos(): Flow<List<Item>>

    suspend fun insertItem(item : Item)

    suspend fun deleteItem(item: Item) : Int
    fun getDetailState(name: String): Flow<Item>?
}