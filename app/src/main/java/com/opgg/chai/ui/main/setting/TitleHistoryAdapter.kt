package com.opgg.chai.ui.main.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemTitleHistoryBinding
import com.opgg.chai.model.data.title.TitleHistoryItem

class TitleHistoryAdapter: RecyclerView.Adapter<TitleHistoryAdapter.TitleHistoryVH>() {
    val items = ArrayList<TitleHistoryItem>()

    inner class TitleHistoryVH(val binding: ItemTitleHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TitleHistoryItem) {
            binding.item = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleHistoryVH {
        val view = DataBindingUtil.inflate<ItemTitleHistoryBinding>(LayoutInflater.from(parent.context), R.layout.item_title_history, parent, false)
        return TitleHistoryVH(view)
    }

    override fun onBindViewHolder(holder: TitleHistoryVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItems(_items: List<TitleHistoryItem>) {
        items.clear()
        items.addAll(_items)

        notifyDataSetChanged()
    }

}