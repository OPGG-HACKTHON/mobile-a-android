package com.opgg.chai.model.data.auth


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TierInfo(
    @Json(name = "leaguePoints")
    val leaguePoints: Int?,
    @Json(name = "rank")
    val rank: String?,
    @Json(name = "tier")
    val tier: String?
)