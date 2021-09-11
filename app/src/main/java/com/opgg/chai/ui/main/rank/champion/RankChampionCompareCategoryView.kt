package com.opgg.chai.ui.main.rank.champion

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemCompareCategoryBinding
import com.opgg.chai.databinding.ViewRankChampionCompareCategoryBinding
import com.opgg.chai.model.data.CompareCategoryItem

class RankChampionCompareCategoryView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private var bind: ViewRankChampionCompareCategoryBinding
            = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_rank_champion_compare_category, this, true)
    private var compareCategoryButtons: MutableList<ItemCompareCategoryBinding> = mutableListOf()
    private var items: List<CompareCategoryItem>? = null

    private var checkedView: CheckBox? = null
    private var listener: ((Boolean) -> Unit)? = null

    fun setData(items: List<CompareCategoryItem>) {
        this.items = items
        items.forEach {
            val itemBinding = DataBindingUtil.inflate<ItemCompareCategoryBinding>(LayoutInflater.from(context), R.layout.item_compare_category, null, false).apply {
                container = this@RankChampionCompareCategoryView
                item = it
            }
            bind.layoutCompareCategory.addView(itemBinding.root)
            compareCategoryButtons.add(itemBinding)
        }
    }

    fun onCheckedChange(view: View) {
        val checkbox = view as CheckBox
        if(checkedView != null) {
            if(checkedView != checkbox) {
                checkedView!!.isChecked = false
                checkedView = checkbox
            }
        } else {
            checkedView = checkbox
        }

        this.listener?.invoke(checkedView!!.isChecked)
    }

    fun setListener(listener: (Boolean) -> Unit) {
        this.listener = listener
    }
}