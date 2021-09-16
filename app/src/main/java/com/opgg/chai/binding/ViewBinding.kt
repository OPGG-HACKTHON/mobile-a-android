package com.opgg.chai.binding

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.opgg.chai.R
import com.opgg.chai.util.extension.loadBackground
import com.opgg.chai.util.extension.randomColor

@BindingAdapter("backgroundImage")
fun bindBackground(view: View, imageResource: Int?) {
    imageResource?.let {
        if(it != 0) {
            view.loadBackground(it)
        }
    }
}

@BindingAdapter("randomBackgroundImage")
fun bindRandomBackground(view: View, size: Int?) {
    val resource = if(size == 0) R.drawable.bg_random_big_title else R.drawable.bg_random_small_title
    val drawable = ContextCompat.getDrawable(view.context, resource) as GradientDrawable?
    drawable?.setColor(Color().randomColor())
    view.loadBackground(drawable as Drawable)
}
