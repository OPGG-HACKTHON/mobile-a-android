package com.opgg.chai.model.data.auth


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResult(
    @Json(name = "accessToken")
    val accessToken: String,
    @Json(name = "authFrom")
    val authFrom: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "message")
    val message: String
)
