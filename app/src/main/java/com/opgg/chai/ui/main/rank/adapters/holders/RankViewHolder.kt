package com.opgg.chai.ui.main.rank.adapters.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemRankBinding
import com.opgg.chai.databinding.ItemRankBodyBinding
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.ui.base.BaseViewHolder

class RankViewHolder(view: View): BaseViewHolder<ItemRankBodyBinding, RankItem>(view) {

    companion object {
        fun newInstance(parent: ViewGroup) : RankViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank_body, parent, false)
            return RankViewHolder(view)
        }
    }

    init {
        binding.rankItemLayout.root.setOnClickListener {
            itemClick?.invoke(it, adapterPosition)
        }
    }

    var itemClick: ((View, Int) -> Unit)? = null


    override fun onBind(item: RankItem) {
        binding.data = item

        binding.rankItemLayout.battleTitle.visibility = if(item.fieldTitle != "") {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

}