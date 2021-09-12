package com.opgg.chai.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.opgg.chai.util.extension.loadBackground

@BindingAdapter("backgroundImage")
fun bindBackground(view: View, imageResource: Int?) {
    imageResource?.let {
        if(it != 0) {
            view.loadBackground(it)
        }
    }
}
