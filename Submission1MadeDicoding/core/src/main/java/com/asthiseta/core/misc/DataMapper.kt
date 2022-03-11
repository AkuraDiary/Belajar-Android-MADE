package com.asthiseta.core.misc

import com.asthiseta.core.data.source.local.entity.KosEntity
import com.asthiseta.core.data.source.remote.response.ItemResponse
import com.asthiseta.core.domain.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


object DataMapper {
    fun mapResponsesToDomain(input: List<ItemResponse>) : Flow<List<Item>>{
        val dataArray = ArrayList<Item>()
        input.map{
            val item = Item(
                it._id,
                it.name,
                it.description,
                it.address,
                it.imageUrl,
                it.genderRestriction,
                it.available_bedrooms,
                it.total_bedrooms,
                it.price,
                false
            )
            dataArray.add(item)
        }
        return flowOf(dataArray)
    }

    fun mapResponseToDomain(input: List<ItemResponse>):Flow<Item>{
        return flowOf(
            Item(
                input[0]._id,
                input[0].name,
                input[0].description,
                input[0].address,
                input[0].imageUrl,
                input[0].genderRestriction,
                input[0].available_bedrooms,
                input[0].total_bedrooms,
                input[0].price,
                false
            )
        )
    }

    fun mapEntitiesToDomain(input: List<KosEntity>): List<Item> =
        input.map{KosEntity->
            Item(
                KosEntity.id,
                KosEntity.name,
                KosEntity.description,
                KosEntity.address,
                KosEntity.imageUrl,
                KosEntity.genderRestriction,
                KosEntity.available_bedrooms,
                KosEntity.total_bedrooms,
                KosEntity.price,
                KosEntity.isFav
            )
        }

    fun mapEntityToDomain(input : KosEntity?): Item{
        return Item(
            input?.id,
            input?.name,
            input?.description,
            input?.address,
            input?.imageUrl,
            input?.genderRestriction,
            input?.available_bedrooms,
            input?.total_bedrooms,
            input?.price,
            input?.isFav
        )
    }

    fun mapDomainToEntity(input:Item) = KosEntity(
        input._id!!,
        input.name,
        input.description,
        input.address,
        input.imageUrl,
        input.genderRestriction,
        input.available_bedrooms,
        input.total_bedrooms,
        input.price,
        input.isFav
    )
}