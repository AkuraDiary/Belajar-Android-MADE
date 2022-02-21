package com.example.myreactivesearch.model

import com.google.gson.annotations.SerializedName

data class PlacesItem(
    @field:SerializedName("place_names")
    val placeName : String
)
