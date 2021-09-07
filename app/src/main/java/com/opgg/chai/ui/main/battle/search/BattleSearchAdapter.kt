package com.opgg.chai.ui.main.battle.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemBattleBinding
import com.opgg.chai.model.data.battle.BattleUser

class BattleSearchAdapter: RecyclerView.Adapter<BattleSearchAdapter.BattleSearchVH>() {
    private val list = ArrayList<BattleUser>()

    inner class BattleSearchVH(val binding: ItemBattleBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BattleUser) {
            binding.item = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattleSearchVH {
        val binding = DataBindingUtil.inflate<ItemBattleBinding>(LayoutInflater.from(parent.context), R.layout.item_battle, parent, false)
        return BattleSearchVH(binding)
    }

    override fun onBindViewHolder(holder: BattleSearchVH, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            holder.binding.battleCharacter.apply {
                visibility = View.VISIBLE
                setOnClickListener { setBattleResultPage(it, resources.getString(R.string.battle_character)) }
            }
            holder.binding.battleTotal.apply {
                visibility = View.VISIBLE
                setOnClickListener { setBattleResultPage(it, resources.getString(R.string.battle_total)) }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun addData(data: List<BattleUser>) {
        list.clear()
        list.addAll(data)

        notifyDataSetChanged()
    }

    //배틀 결과 페이지 이동
    private fun setBattleResultPage(view: View, title: String) {
        val bundle = bundleOf("title" to title)
        view.findNavController().navigate(R.id.action_battleSearchFragment_to_battleResultFragment, bundle)
    }

}
