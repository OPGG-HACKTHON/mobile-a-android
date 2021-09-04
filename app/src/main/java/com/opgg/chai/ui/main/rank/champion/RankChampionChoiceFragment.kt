package com.opgg.chai.ui.main.rank.champion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentRankChampionChoiceBinding
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
                rankChampionSubmit.isEnabled = it
            }
        }
        subscribeObserver()
        vm.loadChampions() // todo dummy data 제거 예정
    }

    private fun subscribeObserver() {
        vm.champions.observe(viewLifecycleOwner) {
            binding.adapter?.submitList(it)
        }
    }

}