package com.opgg.chai.ui.main.setting

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemTitleBinding
import com.opgg.chai.model.data.auth.Title

class TitleItemAdapter(val viewModel: TitleViewModel): RecyclerView.Adapter<TitleItemAdapter.TitleItemVH>() {
    val items = ArrayList<Title>()
    val activateButton = MutableLiveData<Boolean>(false)
    var select: Title? = null
    var prevSelect: Int = -1

    inner class TitleItemVH(val binding: ItemTitleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Title) {
            binding.item = item

            select?.let { si ->
                if(si.id!! == item.id!!) {
                    binding.titleName.backgroundTintList = ColorStateList.valueOf(itemView.resources.getColor(R.color.fill_blue))
                    binding.titleName.setTextColor(itemView.resources.getColor(R.color.fill_background))
                } else {
                    binding.titleName.background = itemView.resources.getDrawable(R.drawable.round_rectangle_dash)
                    binding.titleName.backgroundTintList = null
                    binding.titleName.setTextColor(itemView.resources.getColor(R.color.fill_grey100))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleItemVH {
        val binding = DataBindingUtil.inflate<ItemTitleBinding>(LayoutInflater.from(parent.context), R.layout.item_title, parent, false)

        return TitleItemVH(binding)
    }

    override fun onBindViewHolder(holder: TitleItemVH, position: Int) {
        Log.d("adapter","position : $position")
        holder.itemView.setOnClickListener {
            activateButton.value = true
            select = items[position]
            viewModel.setSelectTitle(select)

            notifyDataSetChanged()
        }

        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItems(_s: List<Title>) {
        items.clear()
        items.addAll(_s)

        notifyDataSetChanged()
    }

}