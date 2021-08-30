package com.opgg.chai.ui.auth.join.search

import com.opgg.chai.model.data.auth.SchoolInfo

interface JoinSearchItemListener {
    fun seletedItem(item: SchoolInfo)
}