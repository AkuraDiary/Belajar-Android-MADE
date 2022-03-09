package com.asthiseta.core.data.source.remote.response


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemResponse(

    @field:Json(name = "_id")
    val _id : String,

    @field:Json(name = "name")
    val name : String,

    @field:Json(name = "description")
    val description : String,

    @field:Json( name="address")
    val address : String,

    @field:Json(name= "imageUrl")
    val imageUrl: String,

    @field:Json(name = "genderRestriction")
    val genderRestriction : String,

    @field:Json( name ="availableBedrooms")
    val available_bedrooms : Int,

    @field:Json(name= "totalBedrooms")
    val total_bedrooms : Int,

    @field:Json(name ="price")
    val price : Int
): Parcelable