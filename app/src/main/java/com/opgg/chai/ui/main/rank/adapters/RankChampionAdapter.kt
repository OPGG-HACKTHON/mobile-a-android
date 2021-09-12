package com.opgg.chai.ui.main.rank.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.windsekirun.koreanindexer.KoreanIndexerRecyclerView
import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.ui.main.rank.adapters.holders.RankChampionViewHolder
import okhttp3.internal.notify

class RankChampionAdapter:
    KoreanIndexerRecyclerView.KoreanIndexerRecyclerAdapter<RankChampionViewHolder>() {

    var items = listOf<ChampionItem>()

    fun submitList(items: List<ChampionItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankChampionViewHolder
            = RankChampionViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: RankChampionViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size
}