package com.asthiseta.core.data

import com.asthiseta.core.data.source.NetworkResources
import com.asthiseta.core.data.source.local.LocalDataSource
import com.asthiseta.core.data.source.remote.RemoteDataSource
import com.asthiseta.core.data.source.remote.network.ApiResponse
import com.asthiseta.core.data.source.remote.response.ItemResponse
import com.asthiseta.core.domain.model.Item
import com.asthiseta.core.domain.repository.IItemRepository
import com.asthiseta.core.misc.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemRepos (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : IItemRepository {
    override fun getAllKos(query: String?): Flow<Resource<List<Item>>> {
        return object : NetworkResources<List<Item>, List<ItemResponse>>(){
            override fun loadFromNetwork(data: List<ItemResponse>): Flow<List<Item>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ItemResponse>>> {
                return remoteDataSource.getAllItem(query)
            }
        }.asFlow()
    }

    override fun getDetailKos(name: String): Flow<Resource<Item>> {
        return object : NetworkResources<Item, ItemResponse>() {
            override fun loadFromNetwork(data: ItemResponse): Flow<Item> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<ItemResponse>> {
                return remoteDataSource.getItemDetail(name)
            }
        }.asFlow()
    }

    override fun getFavoriteKos(): Flow<List<Item>> {
        return localDataSource.getFavoriteItem().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun insertItem(item: Item) {
        val domainItem = DataMapper.mapDomainToEntity(item)
        return localDataSource.insertItem(domainItem)
    }

    override suspend fun deleteItem(item: Item): Int {
        val domainItem = DataMapper.mapDomainToEntity(item)
        return localDataSource.deleteItem(domainItem)
    }
}