package com.opgg.chai.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.opgg.chai.util.extension.loadImage
import com.opgg.chai.util.extension.loadRoundedCornerImage


@BindingAdapter("image")
fun bindImage(view: ImageView, url: String?) {
    url?.let {
        view.loadImage(it)
    }
}

@BindingAdapter("roundedImage")
fun bindRoundedImage(view: ImageView, url: String?) {
    url?.let {
        view.loadRoundedCornerImage(it)
    }
}

@BindingAdapter("image")
fun bindImage(view: ImageView, imageResource: Int?) {
    imageResource?.let {
        view.loadImage(it)
    }
}
