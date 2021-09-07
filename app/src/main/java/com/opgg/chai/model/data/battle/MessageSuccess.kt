package com.opgg.chai.model.data.battle


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MessageSuccess(
    @Json(name = "fromUserId")
    val fromUserId: Int?,
    @Json(name = "fromlolNickname")
    val fromlolNickname: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "receiverAccept")
    val receiverAccept: Boolean?,
    @Json(name = "receiverSeen")
    val receiverSeen: Boolean?,
    @Json(name = "toUserId")
    val toUserId: Int?,
    @Json(name = "tololNickname")
    val tololNickname: String?,
    @Json(name = "type")
    val type: String?
)
