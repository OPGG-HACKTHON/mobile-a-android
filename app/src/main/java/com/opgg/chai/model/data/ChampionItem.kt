package com.opgg.chai.model.data

data class ChampionItem(
    val id: Int,
    val name: String,
    val image: String,
    var isChecked: Boolean = false
)