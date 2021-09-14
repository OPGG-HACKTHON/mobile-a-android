package com.opgg.chai.ui.main.rank.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.ui.main.rank.adapters.holders.RankBottomViewHolder
import com.opgg.chai.ui.main.rank.adapters.holders.RankHeaderViewHolder
import com.opgg.chai.ui.main.rank.adapters.holders.RankViewHolder
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.ui.base.BaseViewHolder

class RankAdapter: RecyclerView.Adapter<BaseViewHolder<*, *>>() {

    companion object {
        const val TYPE_RANK_HEADER = 1
        const val TYPE_RANK = 2
        const val TYPE_RANK_BOTTOM = 3
    }

    var items: MutableList<RankItem> = mutableListOf()
    private var onItemClick: ((View, RankItem) -> Unit)? = null

    fun setListener(listener: ((View, RankItem) -> Unit)?) {
        this.onItemClick = listener
    }

    fun submitList(items: List<RankItem>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> {
        return when(viewType) {
            TYPE_RANK_HEADER -> RankHeaderViewHolder.newInstance(parent)
            TYPE_RANK -> RankViewHolder.newInstance(parent).apply {
                itemClick = { view , i ->
                    this@RankAdapter.onItemClick?.invoke(view, items[i])
                }
            }
            TYPE_RANK_BOTTOM -> RankBottomViewHolder.newInstance(parent)
            else -> RankViewHolder.newInstance(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        when (holder) {
            is RankViewHolder -> {
                holder.onBind(items[position])
            }
            is RankHeaderViewHolder -> {
                holder.onBind(items[position].title)
            }
            is RankBottomViewHolder -> {}
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].viewType) {
            "HEADER" -> {
                TYPE_RANK_HEADER
            }
            "FOOTER" -> {
                TYPE_RANK_BOTTOM
            }
            else -> {
                TYPE_RANK
            }
        }
    }
}