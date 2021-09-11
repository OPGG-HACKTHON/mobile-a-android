package com.opgg.chai.util.extension

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

fun View.loadBackground(imageResource: Int) {
    this.background = ContextCompat.getDrawable(context, imageResource)
}
