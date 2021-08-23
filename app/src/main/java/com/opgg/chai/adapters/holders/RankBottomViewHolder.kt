package com.opgg.chai.adapters.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemRankBottomBinding
import com.opgg.chai.ui.base.BaseViewHolder

class RankBottomViewHolder(parent: View): BaseViewHolder<ItemRankBottomBinding, Any>(parent) {

    companion object {
        fun newInstance(parent: ViewGroup) : RankBottomViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank_bottom, parent, false)
            return RankBottomViewHolder(view)
        }
    }

    override fun onBind(item: Any) {
    }
}