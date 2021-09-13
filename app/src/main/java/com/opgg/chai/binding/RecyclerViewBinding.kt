package com.opgg.chai.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.model.data.auth.SchoolInfo
import com.opgg.chai.model.data.auth.Title
import com.opgg.chai.model.data.title.TitleHistoryItem
import com.opgg.chai.ui.auth.join.search.JoinSearchAdapter
import com.opgg.chai.ui.main.rank.adapters.RankChampionAdapter
import com.opgg.chai.ui.main.setting.TitleHistoryAdapter
import com.opgg.chai.ui.main.setting.TitleItemAdapter

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

@BindingAdapter("addHistory")
fun addHistory(view: RecyclerView, titleHistory: List<TitleHistoryItem>?) {
    titleHistory?.let {
        val adapter = view.adapter as TitleHistoryAdapter
        adapter.addItems(it)
    }
}

@BindingAdapter("addTitleList")
fun addTitle(view: RecyclerView, titleHistory: List<Title>?) {
    titleHistory?.let {
        val adapter = view.adapter as TitleItemAdapter
        adapter.addItems(it)
    }
}

