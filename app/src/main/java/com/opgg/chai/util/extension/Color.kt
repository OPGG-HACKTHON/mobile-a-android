package com.opgg.chai.util.extension

import android.graphics.Color

fun Color.randomColor(): Int {
    val colors = arrayOf(
        "#E9FF01",
        "#01E1FF",
        "#A05CD8",
        "#00FF95",
        "#FFAD00",
        "#00FF38",
        "#00B2FF",
        "#FF7A00")

    val randomNumber = kotlin.random.Random.nextInt(colors.size)
    return Color.parseColor(colors[randomNumber])
}