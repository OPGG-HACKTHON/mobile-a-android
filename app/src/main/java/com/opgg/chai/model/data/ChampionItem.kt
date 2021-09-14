package com.opgg.chai.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChampionItem(
    val id: Int,
    val name: String,
    val image: String,
    val bigImage: String,
    var isChecked: Boolean = false
): Parcelable