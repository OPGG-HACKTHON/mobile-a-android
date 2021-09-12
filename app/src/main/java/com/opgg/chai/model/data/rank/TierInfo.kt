package com.opgg.chai.model.data.rank

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TierInfo(
    @Json(name = "tier") val tier: String,
    @Json(name = "rank") val rank: String,
    @Json(name = "leaguePoints") val leaguePoints: Int)