package com.asthiseta.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class ListItemResponse (
    @field:Json(name = "items")
    val items: List<ItemResponse>
)