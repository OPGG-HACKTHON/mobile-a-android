package com.opgg.chai.model.data.rank

import com.opgg.chai.model.data.RankItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RankInSchoolData(
    @field:Json(name = "seqNo") val seqNo: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "lol") val lol: Lol
) {

    fun parserRankItem(): RankItem {
        return RankItem(id = id, name = "", score = "${lol.tierInfo.tier} ${lol.tierInfo.rank} ${lol.tierInfo.leaguePoints}", isRankUp = false, rank = seqNo.toString())
    }
}