package com.opgg.chai.ui.main.rank.champion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentRankChampionChoiceBinding
import com.opgg.chai.ui.main.rank.adapters.RankChampionAdapter
import com.opgg.chai.ui.main.rank.adapters.decoration.ChampionChoiceDecoration
import com.opgg.chai.util.extension.dp
import com.opgg.chai.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankChampionChoiceFragment: Fragment() {

    private val vm: RankChampionChoiceViewModel by viewModels()
    private lateinit var binding: FragmentRankChampionChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rank_champion_choice, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fragment = this@RankChampionChoiceFragment
            vm = this@RankChampionChoiceFragment.vm
            adapter = RankChampionAdapter()
//            binding.rankChampionList.addItemDecoration(ChampionChoiceDecoration(spacing = 4.dp, spanCount = 3))
            binding.rankChampionList.setOnItemClickListener { position ->
                if(position != -1) {
                    val adapter = binding.rankChampionList.adapter as RankChampionAdapter
                    val clickedItem = adapter.items[position]

                    if(this@RankChampionChoiceFragment.vm.choseChampion != null && this@RankChampionChoiceFragment.vm.choseChampion != clickedItem) {
                        this@RankChampionChoiceFragment.vm.choseChampion?.isChecked = false
                        adapter.notifyItemChanged(adapter.items.indexOf(this@RankChampionChoiceFragment.vm.choseChampion))
                    }
                    if (clickedItem.isChecked) {
                        clickedItem.isChecked = false
                        this@RankChampionChoiceFragment.vm.choseChampion = null
                    } else {
                        clickedItem.isChecked = true
                        this@RankChampionChoiceFragment.vm.choseChampion = clickedItem
                    }
                    rankChampionStep1Submit.isEnabled =
                        this@RankChampionChoiceFragment.vm.choseChampion != null
                    adapter.notifyItemChanged(position)

                }
            }
        }
        subscribeObserver()
        vm.loadChampions()
        vm.loadCompareCategories()
    }

    private fun subscribeObserver() {
        vm.champions.observe(viewLifecycleOwner) {
            binding.rankChampionList.setKeywordList(ArrayList(it.map { it.name }.toList()))
            binding.adapter?.submitList(it)
        }

        vm.compareCategories.observe(viewLifecycleOwner) {
            it?.let { compareCategories ->
                binding.rankChampionCategoryChoiceLayout.setData(compareCategories)
                binding.rankChampionCategoryChoiceLayout.setListener {  choseCompareCategoryItem ->
                    binding.rankChampionStep2DataSubmit.isEnabled = choseCompareCategoryItem != null
                    vm.choseCompareCategory = choseCompareCategoryItem
                }
            }
        }

        vm.showResultFragmentEvent.observeEvent(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_rankChampionChoiceFragment_to_rankChampionFragment,
                bundleOf("champion" to vm.choseChampion, "compareCategory" to vm.choseCompareCategory)
            )
        }
    }

    fun onClose(view: View) {
        view.findNavController().navigateUp()
    }

    fun onChampionSubmit(view: View) {
        moveStep2()
    }

    fun onBack(view: View) {
        moveStep1()
    }

    fun onDataSubmit(view: View) {
        vm.showChampionResultEvent()
    }

    private fun moveStep1() {
        binding.rankChampionChoiceProgress.progress = 50
        binding.rankChampionStepFlipper.showPrevious()
    }

    private fun moveStep2() {
        binding.rankChampionChoiceProgress.progress = 100
        binding.rankChampionStepFlipper.showNext()
    }

}