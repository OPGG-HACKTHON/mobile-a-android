package com.opgg.chai.ui.main.rank.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentRankChampionBinding
import com.opgg.chai.ui.main.rank.adapters.RankAdapter
import com.opgg.chai.util.DisplayUtil

class RankChampionFragment: Fragment() {

    private val vm: RankChampionViewModel by viewModels()
    private lateinit var binding: FragmentRankChampionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rank_champion, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fragment = this@RankChampionFragment
            vm = this.vm
            adapter = RankAdapter()
        }
        subscribeObserver()

        vm.loadCompareCategories()
        vm.loadRanks()
    }

    private fun subscribeObserver() {
        vm.compareCategories.observe(viewLifecycleOwner) {
            val params = RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT)
            params.marginStart = resources.getDimensionPixelSize(R.dimen._4dp)
            params.marginEnd = resources.getDimensionPixelSize(R.dimen._4dp)
            it.forEach {
                val item = RadioButton(requireContext()).apply {
                    text = it.name
                    setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.selector_compare_category_tab))
                    setBackgroundResource(R.drawable.bg_compare_category_tab)
                    buttonDrawable = null
                    setPadding(
                        resources.getDimensionPixelSize(R.dimen._12dp),
                        resources.getDimensionPixelSize(R.dimen._8dp),
                        resources.getDimensionPixelSize(R.dimen._12dp),
                        resources.getDimensionPixelSize(R.dimen._8dp))
                    layoutParams = params
                }
                binding.rankChampionCategoryGroup.addView(item)
            }
        }

        vm.rankItem.observe(viewLifecycleOwner) {
            binding.adapter?.submitList("", it)
        }
    }

    fun onCloseClick(view: View) {
        view.findNavController().navigate(R.id.action_rankChampionFragment_to_rankFragment)
    }
}
