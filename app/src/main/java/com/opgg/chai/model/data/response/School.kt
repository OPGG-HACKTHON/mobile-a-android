package com.opgg.chai.model.data.response

import com.opgg.chai.model.data.RankItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.NumberFormat

@JsonClass(generateAdapter = true)
data class School (
    @Json(name = "rankNo")
    val rankNo: Int,
    @Json(name = "rankChangedStatus")
    val rankChangedStatus: String,
    @Json(name = "point")
    val point: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "division")
    val division: String,
    @Json(name = "region")
    val region: String,
    @Json(name = "address")
    val address: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "updatedAt")
    val updatedAt: String
) {

    fun parserRankItem(me: Boolean = false): RankItem {
        return RankItem(
            id = id,
            name = name,
            score = "통합점수 ${NumberFormat.getInstance().format(point)} LP",
            isRankUp = rankChangedStatus,
            rank = rankNo.toString(),
            me = me)
    }
}