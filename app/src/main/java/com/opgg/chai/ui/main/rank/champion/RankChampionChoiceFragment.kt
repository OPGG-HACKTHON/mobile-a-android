package com.opgg.chai.ui.main.rank.champion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentRankChampionChoiceBinding
import com.opgg.chai.databinding.ItemCompareCategoryBinding
import com.opgg.chai.ui.main.rank.adapters.RankChampionAdapter

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
            vm = this.vm
            adapter = RankChampionAdapter {
                rankChampionStep1Submit.isEnabled = it
            }
        }
        subscribeObserver()
        vm.loadChampions() // todo dummy data 제거 예정
        vm.loadCompareCategories()
    }

    private fun subscribeObserver() {
        vm.champions.observe(viewLifecycleOwner) {
            binding.adapter?.submitList(it)
        }

        vm.compareCategories.observe(viewLifecycleOwner) {
            it?.let { compareCategories ->
                binding.rankChampionCategoryChoiceLayout.setData(compareCategories)
                binding.rankChampionCategoryChoiceLayout.setListener {  isChecked ->
                    binding.rankChampionStep2DataSubmit.isEnabled = isChecked
                }
            }
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
        view.findNavController().navigate(R.id.action_rankChampionChoiceFragment_to_rankChampionFragment)
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