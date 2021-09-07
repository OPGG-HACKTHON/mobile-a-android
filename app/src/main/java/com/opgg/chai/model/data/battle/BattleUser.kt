package com.opgg.chai.model.data.battle


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BattleUser(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "lol")
    val lol: Lol?
) {
    fun toDictionary(): HashMap<String, Any?> {
        val resultMap = HashMap<String, Any?>()
        resultMap.put("id", id)
        resultMap.put("lol", lol)

        return resultMap
    }
}
