package com.opgg.chai.ui.main.rank.adapters.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemRankChampionBinding
import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.ui.base.BaseViewHolder


class RankChampionViewHolder(parent: View): BaseViewHolder<ItemRankChampionBinding, ChampionItem>(
    parent
) {

    companion object {
        fun newInstance(parent: ViewGroup) : RankChampionViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_rank_champion,
                parent,
                false
            )
            return RankChampionViewHolder(view)
        }
    }


    override fun onBind(item: ChampionItem) {
        binding.item = item
        binding.executePendingBindings()
    }
}