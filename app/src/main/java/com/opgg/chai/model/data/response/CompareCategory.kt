package com.opgg.chai.model.data.response

import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.model.data.CompareCategoryItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompareCategory(
    @Json(name = "category") val category: String,
    @Json(name = "fields") val field: List<CategoryField>
)