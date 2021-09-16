package com.opgg.chai.util.extension

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat

fun View.loadBackground(imageResource: Int) {
    this.background = ContextCompat.getDrawable(context, imageResource)
}

fun View.loadBackground(drawable: Drawable) {
    this.background = drawable
}