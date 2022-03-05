package com.asthiseta.core.data.source.remote

import android.util.Log
import com.asthiseta.core.data.source.remote.network.ApiResponse
import com.asthiseta.core.data.source.remote.network.ClientApi
import com.asthiseta.core.data.source.remote.response.ItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val clientApi : ClientApi) {

    suspend fun getAllItem(query : String?): Flow<ApiResponse<List<ItemResponse>>> =
        flow {
            try {
                val userSearch = clientApi.searchForItem(query)
                val itemArray = userSearch.items
                if(itemArray.isNullOrEmpty()){
                    emit(ApiResponse.IsError(null))
                }else{
                    emit(ApiResponse.IsSuccess(itemArray))
                }
            }catch (e : Exception){
                emit(ApiResponse.IsError(e.toString()))
                //Log.e(RemoteDataSource::class.java.simpleName, e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getItemDetail(name : String): Flow<ApiResponse<ItemResponse>> =
        flow {
            try {
                val itemDetail = clientApi.searchForItemDetail(name)
                emit(ApiResponse.IsSuccess(itemDetail))
            }catch (e : Exception){
                emit(ApiResponse.IsError(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}