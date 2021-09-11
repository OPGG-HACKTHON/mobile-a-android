package com.opgg.chai.ui.main.rank.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.ui.main.rank.adapters.holders.RankChampionViewHolder

class RankChampionAdapter(private val onSelectedChangeListener: (Boolean) -> Unit): ListAdapter<ChampionItem, RankChampionViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChampionItem>() {
            override fun areContentsTheSame(oldItem: ChampionItem, newItem: ChampionItem) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ChampionItem, newItem: ChampionItem) =
                oldItem.id == newItem.id
        }
    }

    private var checkedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankChampionViewHolder
            = RankChampionViewHolder.newInstance(parent).apply {
        itemClick = { view, i ->
            notifyItemChanged(i)
            checkedPosition = if(!getItem(i).isChecked) {
                -1
            } else {
                if(checkedPosition != -1) {
                    getItem(checkedPosition).isChecked = false
                }
                notifyItemChanged(checkedPosition)
                i
            }
            onSelectedChangeListener.invoke(checkedPosition != -1)
        }
    }

    override fun onBindViewHolder(holder: RankChampionViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}