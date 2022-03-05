package com.asthiseta.core.data

import com.asthiseta.core.data.source.local.LocalDataSource
import com.asthiseta.core.data.source.remote.RemoteDataSource
import com.asthiseta.core.domain.model.Item
import com.asthiseta.core.domain.repository.IItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepos (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : IItemRepository {
    override fun getAllKos(query: String?): Flow<Resource<List<Item>>> {
        TODO("Not yet implemented")
    }

    override fun getDetailKos(name: String): Flow<Resource<Item>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteKos(): Flow<List<Item>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertItem(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(item: Item): Int {
        TODO("Not yet implemented")
    }
}