package com.opgg.chai.model.data.rank

import com.opgg.chai.model.data.RankItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.NumberFormat

@JsonClass(generateAdapter = true)
data class RankInSchoolData(
    @Json(name = "id") val id: Int,
    @Json(name = "lol") val lol: Lol,
    @Json(name = "rankNo") val rankNo: Int?,
    @Json(name = "value") val value: String?,
    @Json(name = "fieldName") val fieldName: String?,
    @Json(name = "rankChangedStatus") val rankChangedStatus: String?) {

    fun parserRankItem(me: Boolean = false): RankItem {
        return RankItem(
            id = id.toString(),
            image = lol.profileIconImageUrl,
            name = lol.name,
            score = "${lol.tierInfo.tier} ${lol.tierInfo.rank} ${NumberFormat.getInstance().format(lol.tierInfo.leaguePoints)} LP",
            summonerLevel = lol.summonerLevel.toString(),
            isRankUp = rankChangedStatus ?: "NEW",
            rank = rankNo.toString() ?: "",
            me = me)
    }

    fun parserChampionRankItem(me: Boolean = false): RankItem {
        return RankItem(
            id = id.toString(),
            image = lol.profileIconImageUrl,
            name = lol.name,
            score = "${fieldName ?: ""} ${value ?: ""}",
            summonerLevel = lol.summonerLevel.toString(),
            isRankUp = rankChangedStatus ?: "NEW",
            rank = rankNo.toString() ?: "",
            me = me)
    }
}