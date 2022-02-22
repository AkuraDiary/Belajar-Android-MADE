package com.dicoding.tourismapp.core.data.source.local.room

import androidx.room.*
import com.dicoding.tourismapp.core.data.source.local.entity.TourismEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface TourismDao {

    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flow<List<TourismEntity>>

    @Query("SELECT * FROM tourism where isFavorite = 1")
    fun getFavoriteTourism(): Flow<List<TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<TourismEntity>) : Completable

    @Update
    fun updateFavoriteTourism(tourism: TourismEntity)
}
