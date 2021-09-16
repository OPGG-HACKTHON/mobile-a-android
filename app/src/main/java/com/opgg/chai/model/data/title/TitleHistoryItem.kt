package com.opgg.chai.model.data.title


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TitleHistoryItem(
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "exposureName")
    val exposureName: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "titleStatus")
    val titleStatus: String?,
    @Json(name = "logValue")
    val logValue: String?,
)
