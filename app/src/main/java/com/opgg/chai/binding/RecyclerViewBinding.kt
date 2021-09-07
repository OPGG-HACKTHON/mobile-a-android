package com.opgg.chai.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.model.data.auth.SchoolInfo
import com.opgg.chai.model.data.battle.BattleUser
import com.opgg.chai.ui.auth.join.search.JoinSearchAdapter
import com.opgg.chai.ui.main.battle.search.BattleSearchAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("addSchoolData")
fun addSchoolInfo(view: RecyclerView, schoolList: List<SchoolInfo>?) {
    schoolList?.let {
        val adapter = view.adapter as JoinSearchAdapter
        adapter.addItems(it)
    }
}

@BindingAdapter("addBattleUser")
fun addBattleUser(view: RecyclerView, battleUserList: List<BattleUser>?) {
    battleUserList?.let {
        (view.adapter as BattleSearchAdapter).addData(it)
    }
}
