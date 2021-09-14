package com.opgg.chai.ui.main.rank.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.ui.base.BaseViewHolder
import com.opgg.chai.ui.main.rank.adapters.holders.*

class RankCompareChampionAdapter: ListAdapter<RankItem, BaseViewHolder<*, *>>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RankItem>() {
            override fun areContentsTheSame(oldItem: RankItem, newItem: RankItem) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: RankItem, newItem: RankItem) =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> {
        return when(viewType) {
            0 -> RankCompareChampionViewHolder.newInstance(parent)
            else -> RankSpaceViewHolder.newInstance(parent)
            }
        }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        if(holder is RankCompareChampionViewHolder)
            holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == itemCount - 1) 1 else 0
    }
}