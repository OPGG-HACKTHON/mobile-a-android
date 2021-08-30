package com.opgg.chai.util

import android.content.Context

object DisplayUtil {
    fun convertDpToPixel(context: Context, dp: Float) : Float =
        dp * (context.resources.displayMetrics.densityDpi / 160f)
}