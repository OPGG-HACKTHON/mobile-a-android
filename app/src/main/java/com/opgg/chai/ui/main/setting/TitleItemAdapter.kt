package com.opgg.chai.ui.main.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemTitleBinding
import com.opgg.chai.model.data.title.TitleItem

class TitleItemAdapter: RecyclerView.Adapter<TitleItemAdapter.TitleItemVH>() {
    val items = ArrayList<TitleItem>()

    inner class TitleItemVH(val binding: ItemTitleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TitleItem) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleItemVH {
        val binding = DataBindingUtil.inflate<ItemTitleBinding>(LayoutInflater.from(parent.context), R.layout.item_title, parent, false)

        return TitleItemVH(binding)
    }

    override fun onBindViewHolder(holder: TitleItemVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItems(_items: List<TitleItem>) {
        items.clear()
        items.addAll(_items)

        notifyDataSetChanged()
    }
}