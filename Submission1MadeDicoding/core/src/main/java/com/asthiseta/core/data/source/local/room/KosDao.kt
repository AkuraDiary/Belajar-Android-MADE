package com.asthiseta.core.data.source.local.room

import androidx.room.*
import com.asthiseta.core.data.source.local.entity.KosEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface KosDao {
    @Query("SELECT * FROM kos_table")
    fun getFavoriteItem(): Flow<List<KosEntity>>

    @Query("SELECT * from kos_table WHERE name = :name")
    fun getDetails(name : String): Flow<KosEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(kos : KosEntity?)

    @Delete
    suspend fun deleteItem(kos : KosEntity) : Int
}