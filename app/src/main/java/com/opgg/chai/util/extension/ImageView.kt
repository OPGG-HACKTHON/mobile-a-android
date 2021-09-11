package com.opgg.chai.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.opgg.chai.R
import com.opgg.chai.util.DisplayUtil


fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .override(this.width, this.height)
        .into(this)
}

fun ImageView.loadImage(imageResource: Int) {
    Glide.with(this)
        .load(imageResource)
        .override(this.width, this.height)
        .into(this)
}



fun ImageView.loadRoundedCornerImage(url: String) {
    Glide.with(this)
        .load(url)
        .override(this.width, this.height)
        .apply(RequestOptions().transform(RoundedCorners(resources.getDimensionPixelSize(R.dimen._16dp))))
        .into(this)
}