package com.opgg.chai.model.data.auth


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SchoolInfo(
    @Json(name = "address")
    val address: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "division")
    val division: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "regionId")
    val regionId: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)