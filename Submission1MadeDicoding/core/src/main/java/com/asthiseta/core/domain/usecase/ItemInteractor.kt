package com.asthiseta.core.domain.usecase

import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.Item
import com.asthiseta.core.domain.repository.IItemRepository
import kotlinx.coroutines.flow.Flow

class ItemInteractor(private val itemRepos: IItemRepository) : ItemUseCase {
    override fun getAllKos(query: String?): Flow<Resource<List<Item>>> {
        return itemRepos.getAllKos(query)
    }

    override fun getDetailKos(name: String): Flow<Resource<Item>> {
        return itemRepos.getDetailKos(name)
    }

    override fun getDetailState(name: String): Flow<Item>? = itemRepos.getDetailState(name)

    override fun getFavoriteItem(): Flow<List<Item>> {
        return itemRepos.getFavoriteKos()
    }

    override suspend fun insertItem(item: Item) =
        itemRepos.insertItem(item)


    override suspend fun deleteItem(item: Item): Int =
        itemRepos.deleteItem(item)
}