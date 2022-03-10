package com.asthiseta.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kos_table")
data class KosEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id : String,

    @ColumnInfo(name = "name")
    var name : String?,

    @ColumnInfo(name = "description")
    val description : String?,

    @ColumnInfo(name = "address")
    val address : String?,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,

    @ColumnInfo(name = "genderRestriction")
    val genderRestriction : String?,

    @ColumnInfo(name = "available_bedrooms")
    val available_bedrooms : Int?,

    @ColumnInfo(name = "total_bedrooms")
    val total_bedrooms : Int?,

    @ColumnInfo(name = "price")
    val price : Int?,

    @ColumnInfo(name = "isFav")
    var isFav : Boolean?
)