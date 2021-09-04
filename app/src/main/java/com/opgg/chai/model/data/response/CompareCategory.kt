package com.opgg.chai.model.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompareCategory(
    @Json(name = "id") val id: Int,
    @Json(name = "lolMatchFieldName") val lolMatchFieldName: String,
    @Json(name = "category") val category: String,
    @Json(name = "name") val name :String,
    @Json(name = "enName") val enName: String
)