package com.asthiseta.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(

    @field:SerializedName("_id")
    val _id : String,

    @field:SerializedName("name")
    val name : String,

    @field:SerializedName("description")
    val description : String,

    @field:SerializedName( "address")
    val address : String,

    @field:SerializedName( "imageUrl")
    val imageUrl: String,

    @field:SerializedName("genderRestriction")
    val genderRestriction : String,

    @field:SerializedName( "available_bedrooms")
    val available_bedrooms : Int,

    @field:SerializedName("total_bedrooms")
    val total_bedrooms : Int,

    @field:SerializedName("price")
    val price : Int
)