package com.opgg.chai.model.data.title


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TitleItem(
    @Json(name = "exposureName")
    val exposureName: String?,
    @Json(name = "id")
    val id: Int?
)