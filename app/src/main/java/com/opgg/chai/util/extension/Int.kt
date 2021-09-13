package com.opgg.chai.util.extension

import android.content.res.Resources
import android.util.TypedValue

val Int.dp: Int
    get() {
        val metrics = Resources.getSystem().displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
    }
