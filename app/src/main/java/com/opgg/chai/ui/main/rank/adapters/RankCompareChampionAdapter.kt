package com.opgg.chai.ui.main.rank.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.ui.main.rank.adapters.holders.RankCompareChampionViewHolder

class RankCompareChampionAdapter: ListAdapter<RankItem, RankCompareChampionViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RankItem>() {
            override fun areContentsTheSame(oldItem: RankItem, newItem: RankItem) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: RankItem, newItem: RankItem) =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RankCompareChampionViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: RankCompareChampionViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}