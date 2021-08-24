package com.opgg.chai.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.opgg.chai.R
import com.opgg.chai.util.DisplayUtil


fun ImageView.loadImage(url: String) {
    // todo : sample 이미지 변경
    Glide.with(this)
        .load(url)
        .override(this.width, this.height)
        .placeholder(R.drawable.profile_sample)
        .error(R.drawable.profile_sample)
        .into(this)
}


fun ImageView.loadRoundedCornerImage(url: String) {
    // todo : sample 이미지 변경
    Glide.with(this)
        .load(url)
        .override(this.width, this.height)
        .placeholder(R.drawable.profile_sample)
        .error(R.drawable.profile_sample)
        .apply(RequestOptions().transform(RoundedCorners(DisplayUtil.convertDpToPixel(this.context, 14.toFloat()).toInt())))
        .into(this)
}