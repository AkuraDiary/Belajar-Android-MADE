package com.asthiseta.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val _id : String?,

    val name : String?,

    val description : String?,

    val address : String?,

    val imageUrl: String?,

    val genderRestriction : String?,

    val available_bedrooms : Int?,

    val total_bedrooms : Int?,

    val price : Int?
):Parcelable
