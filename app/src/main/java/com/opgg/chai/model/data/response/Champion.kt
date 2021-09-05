package com.opgg.chai.model.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Champion(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "enName") val enName: String,
    @Json(name = "imageUrl") val imageUrl: String)