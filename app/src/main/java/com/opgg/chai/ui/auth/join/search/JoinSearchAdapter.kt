package com.opgg.chai.ui.auth.join.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemSearchResultBinding
import com.opgg.chai.model.data.auth.SchoolInfo

class JoinSearchAdapter : RecyclerView.Adapter<JoinSearchAdapter.JoinSearchVH>() {
    private val schoolList = ArrayList<SchoolInfo>()

    inner class JoinSearchVH(val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SchoolInfo) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoinSearchVH {
        val binding = DataBindingUtil.inflate<ItemSearchResultBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_search_result,
            parent,
            false
        )

        return JoinSearchVH(binding)
    }

    override fun onBindViewHolder(holder: JoinSearchVH, position: Int) {
        holder.bind(schoolList[position])
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    // 학교 정보 추가
    fun addItems(items: List<SchoolInfo>) {
        schoolList.clear()
        schoolList.addAll(items)

        notifyDataSetChanged()
    }
}