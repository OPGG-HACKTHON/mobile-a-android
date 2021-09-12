package com.opgg.chai.model.data.auth


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "lol")
    val lol: Lol?,
    @Json(name = "school")
    val school: School?,
    @Json(name = "title")
    var title: Title?
)