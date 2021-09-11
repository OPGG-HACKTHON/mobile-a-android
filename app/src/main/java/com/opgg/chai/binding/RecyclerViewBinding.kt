package com.opgg.chai.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.model.data.auth.SchoolInfo
import com.opgg.chai.ui.auth.join.search.JoinSearchAdapter
import com.opgg.chai.ui.main.rank.adapters.RankChampionAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("items")
fun bindSubmitChampions(view: RecyclerView, items: List<ChampionItem>?) {
    items?.let {
        (view.adapter as RankChampionAdapter).submitList(it)
    }
}


@BindingAdapter("addSchoolData")
fun addSchoolInfo(view: RecyclerView, schoolList: List<SchoolInfo>?) {
    schoolList?.let {
        val adapter = view.adapter as JoinSearchAdapter
        adapter.addItems(it)
    }
}
