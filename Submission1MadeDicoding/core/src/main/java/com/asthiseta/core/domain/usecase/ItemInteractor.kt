package com.asthiseta.core.domain.usecase

import com.asthiseta.core.data.ItemRepos
import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.Item
import kotlinx.coroutines.flow.Flow

class ItemInteractor(private val itemRepos: ItemRepos) : ItemUseCase {
    override fun getAllKos(query: String?): Flow<Resource<List<Item>>> {
        return itemRepos.getAllKos(query)
    }

    override fun getDetailKos(name: String): Flow<Resource<Item>> {
        return itemRepos.getDetailKos(name)
    }

    override fun getFavoriteItem(): Flow<List<Item>> {
        return itemRepos.getFavoriteKos()
    }

    override suspend fun insertItem(item: Item) =
        itemRepos.insertItem(item)


    override suspend fun deleteItem(item: Item): Int =
        itemRepos.deleteItem(item)
}