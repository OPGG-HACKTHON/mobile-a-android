package com.opgg.chai.adapters.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemRankBinding
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.ui.base.BaseViewHolder

class RankViewHolder(view: View): BaseViewHolder<ItemRankBinding, RankItem>(view) {

    companion object {
        fun newInstance(parent: ViewGroup) : RankViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank, parent, false)
            return RankViewHolder(view)
        }
    }
    override fun onBind(item: RankItem) {
        binding.data = item
    }

}