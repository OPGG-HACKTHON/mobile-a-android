package com.opgg.chai.ui.main.setting

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemTitleBinding
import com.opgg.chai.model.data.auth.Title

class TitleItemAdapter: RecyclerView.Adapter<TitleItemAdapter.TitleItemVH>() {
    val items = ArrayList<Title>()
    val activateButton = MutableLiveData<Boolean>(false)
    var select: Title? = null

    inner class TitleItemVH(val binding: ItemTitleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Title) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleItemVH {
        val binding = DataBindingUtil.inflate<ItemTitleBinding>(LayoutInflater.from(parent.context), R.layout.item_title, parent, false)

        return TitleItemVH(binding)
    }

    override fun onBindViewHolder(holder: TitleItemVH, position: Int) {
        holder.itemView.setOnClickListener {
            activateButton.value = true
            select = items[position]
        }

        select?.let { si ->
            Log.d("adapter", "${si.id} && ${items[position].id}")
            if(si.id!! == items[position].id!!) {
                holder.binding.titleName.backgroundTintList = ColorStateList.valueOf(holder.binding.root.resources.getColor(R.color.fill_blue))
            } else {
                holder.binding.titleName.background = holder.binding.root.resources.getDrawable(R.drawable.round_rectangle_dash)
            }
        }

        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItems(_s: List<Title>) {
        items.clear()
        items.addAll(_s)

        notifyDataSetChanged()
    }

}