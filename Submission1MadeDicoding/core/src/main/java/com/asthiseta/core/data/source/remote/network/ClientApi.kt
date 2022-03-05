package com.asthiseta.core.data.source.remote.network

import com.asthiseta.core.domain.model.Item
import retrofit2.http.GET
import retrofit2.http.Query

interface ClientApi {
    @GET("kosts")
    suspend fun searchForItem(
        @Query("q")q: String?
    ): List<Item>
}