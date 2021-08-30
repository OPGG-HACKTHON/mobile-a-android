package com.opgg.chai.ui.main.rank.adapters.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemRankHeaderBinding
import com.opgg.chai.ui.base.BaseViewHolder

class RankHeaderViewHolder(parent: View): BaseViewHolder<ItemRankHeaderBinding, String>(parent) {

    companion object {
        fun newInstance(parent: ViewGroup) : RankHeaderViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank_header, parent, false)
            return RankHeaderViewHolder(view)
        }
    }
    override fun onBind(item: String) {
        binding.title = item
    }
}