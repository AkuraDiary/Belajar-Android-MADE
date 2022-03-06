package com.asthiseta.core.domain.usecase

import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.Item
import kotlinx.coroutines.flow.Flow


interface ItemUseCase {
    fun getAllKos(query : String?) : Flow<Resource<List<Item>>>

    fun getDetailKos(name : String) : Flow<Resource<Item>>

    fun getFavoriteItem() : Flow<List<Item>>

    suspend fun insertItem(item : Item)
    suspend fun deleteItem(item : Item) : Int
}