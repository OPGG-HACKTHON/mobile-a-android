package com.opgg.chai.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CompareCategoryItem(
    val id: Int,
    val name: String,
    val category: String,
    var isSelected: Boolean = false
): Parcelable