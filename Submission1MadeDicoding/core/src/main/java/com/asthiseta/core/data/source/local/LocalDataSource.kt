package com.asthiseta.core.data.source.local

import com.asthiseta.core.data.source.local.entity.KosEntity
import com.asthiseta.core.data.source.local.room.KosDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource(private val kosDao: KosDao) {

    fun getFavoriteItem() : Flow<List<KosEntity>> = kosDao.getFavoriteItem()
}