package com.asthiseta.core.domain.repository

import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.Item
import kotlinx.coroutines.flow.Flow


interface IItemRepository {
    fun getAllKos(query: String?) : Flow<Resource<List<Item>>>
}