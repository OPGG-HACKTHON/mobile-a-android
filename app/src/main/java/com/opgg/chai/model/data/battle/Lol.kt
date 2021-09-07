package com.opgg.chai.model.data.battle


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Lol(
    @Json(name = "name")
    val name: String?,
    @Json(name = "profileIconId")
    val profileIconId: Int?,
    @Json(name = "profileIconImageUrl")
    val profileIconImageUrl: String?,
    @Json(name = "summonerLevel")
    val summonerLevel: Int?,
    @Json(name = "tierInfo")
    val tierInfo: TierInfo?
)
