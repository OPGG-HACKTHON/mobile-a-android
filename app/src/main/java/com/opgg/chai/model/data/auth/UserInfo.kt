package com.opgg.chai.model.data.auth


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "LOLAccountId")
    val lOLAccountId: String,
    @Json(name = "role")
    val role: String,
    @Json(name = "schoolId")
    val schoolId: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)
