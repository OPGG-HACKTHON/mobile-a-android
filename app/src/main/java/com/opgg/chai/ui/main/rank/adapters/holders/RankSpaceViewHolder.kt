package com.opgg.chai.ui.main.rank.adapters.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemRankSpaceBinding
import com.opgg.chai.ui.base.BaseViewHolder

class RankSpaceViewHolder(parent: View): BaseViewHolder<ItemRankSpaceBinding, String>(parent) {

    companion object {
        fun newInstance(parent: ViewGroup) : RankSpaceViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank_space, parent, false)
            return RankSpaceViewHolder(view)
        }
    }
    override fun onBind(item: String) {
    }
}