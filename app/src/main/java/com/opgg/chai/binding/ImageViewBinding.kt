package com.opgg.chai.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.opgg.chai.util.extension.loadImage

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter("image")
    fun bindImage(view: ImageView, url: String?) {
        url?.let {
            view.loadImage(it)
        }
    }
}