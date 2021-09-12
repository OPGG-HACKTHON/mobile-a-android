package com.opgg.chai.ui.main.rank.champion

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemCompareCategoryBinding
import com.opgg.chai.databinding.ViewRankChampionCompareCategoryBinding
import com.opgg.chai.model.data.CompareCategoryItem

class RankChampionCompareCategoryView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private var bind: ViewRankChampionCompareCategoryBinding
            = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_rank_champion_compare_category, this, true)
    private var items: List<CompareCategoryItem>? = null

    private var checkedView: CheckBox? = null
    private var listener: ((CompareCategoryItem?) -> Unit)? = null
    private var lastCategory = ""

    fun setData(items: List<CompareCategoryItem>) {
        this.items = items
        items.forEach {
            val itemBinding = DataBindingUtil.inflate<ItemCompareCategoryBinding>(LayoutInflater.from(context), R.layout.item_compare_category, null, false).apply {
                container = this@RankChampionCompareCategoryView
                item = it
            }
            if(lastCategory != it.category) {
                lastCategory = it.category

                val text = TextView(context).apply {
                    text = it.category
                    textSize = 18F
                    setTextColor(ContextCompat.getColor(context, R.color.text_primary))
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        resources.getDimensionPixelSize(R.dimen._32dp)
                    ).apply {
                        setMargins(0, resources.getDimensionPixelSize(R.dimen._24dp), 0, 0)
                    }
                }
                bind.layoutCompareCategory.addView(text)
            }
            bind.layoutCompareCategory.addView(itemBinding.root)
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

        val choseItem = if(checkedView!!.isChecked) {
            checkedView!!.tag as CompareCategoryItem
        } else {
            null
        }
        this.listener?.invoke(choseItem)
    }

    fun setListener(listener: (CompareCategoryItem?) -> Unit) {
        this.listener = listener
    }
}