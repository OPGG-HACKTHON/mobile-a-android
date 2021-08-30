package com.opgg.chai.model.data.rank

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Lol(
    @Json(name = "profileIconId") val profileIconId: Int,
    @Json(name = "profileIconImageUrl") val profileIconImageUrl: String,
    @Json(name = "summonerLevel") val summonerLevel: Int,
    @Json(name = "tierInfo") val tierInfo: TierInfo)