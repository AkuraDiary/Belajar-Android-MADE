package com.asthiseta.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListItemResponse (
    @field:SerializedName("items")
    val items: List<ItemResponse>
)