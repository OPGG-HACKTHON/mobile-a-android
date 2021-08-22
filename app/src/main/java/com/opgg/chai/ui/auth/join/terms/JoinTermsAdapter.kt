package com.opgg.chai.ui.auth.join.terms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.opgg.chai.R
import com.opgg.chai.databinding.ItemTermsBinding
import com.opgg.chai.model.data.TermItem

class JoinTermsAdapter : RecyclerView.Adapter<JoinTermsAdapter.JoinTermsVH>() {
    val termList = ArrayList<TermItem>()
    var isSelectAll: MutableLiveData<Boolean> = MutableLiveData(false)
    private var cntCheck = 0

    inner class JoinTermsVH(val binding: ItemTermsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(termItem: TermItem) {
            binding.item = termItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoinTermsVH {
        val binding = DataBindingUtil.inflate<ItemTermsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_terms,
            parent,
            false
        )

        return JoinTermsVH(binding)
    }

    override fun onBindViewHolder(holder: JoinTermsVH, position: Int) {
        holder.bind(termList[position])

        //전체 체크 관리
        holder.binding.termCheck.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                termList[position].isChecked = isChecked
                isSelectAll.value = false
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = termList.size

    fun addTerm(term: List<TermItem>) {
        termList.clear()

        termList.addAll(term)
        notifyDataSetChanged()
    }

    fun selectCheckAll() {
        isSelectAll.value?.let { isCheck ->
            isSelectAll.value = !isCheck

            for (i in termList) {
                i.isChecked = !isCheck
            }

            notifyDataSetChanged()
        }
    }

}