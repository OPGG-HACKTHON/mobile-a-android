package com.opgg.chai.ui.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out B: ViewDataBinding, ITEM>(view: View): RecyclerView.ViewHolder(view) {

    val binding = DataBindingUtil.bind<B>(itemView)!!

    abstract fun onBind(item: ITEM)
}