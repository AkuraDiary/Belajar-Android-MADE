package com.asthiseta.core.data.source

import com.asthiseta.core.data.Resource
import com.asthiseta.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*


abstract class NetworkResources<ResultType, RequestType> {
    private val result : Flow<Resource<ResultType>> = flow{
        emit(Resource.Loading())
        when(val apiResponse = createCall().first()){
            is ApiResponse.IsSuccess -> {
                emitAll(loadFromNetwork(apiResponse.data).map{
                    Resource.Success(it)
                })
            }

            is ApiResponse.IsError -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    fun asFlow(): Flow<Resource<ResultType>> = result
    protected abstract suspend fun createCall():Flow<ApiResponse<RequestType>>
    protected abstract fun loadFromNetwork(data:RequestType) : Flow<ResultType>
}