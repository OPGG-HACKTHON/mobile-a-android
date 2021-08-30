package com.opgg.chai.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.model.data.auth.SchoolInfo
import com.opgg.chai.ui.auth.join.search.JoinSearchAdapter

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
