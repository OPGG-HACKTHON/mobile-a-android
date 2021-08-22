package com.opgg.chai.ui.main.rank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.opgg.chai.R
import com.opgg.chai.adapters.RankAdapter
import com.opgg.chai.databinding.FragmentRankBinding
import com.opgg.chai.databinding.FragmentRankInSchoolBinding
import com.opgg.chai.model.data.RankItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankInSchoolFragment: Fragment()   {

    private val vm: RankInSchoolViewModel by viewModels()
    private lateinit var binding: FragmentRankInSchoolBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rank_in_school, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fragment = this@RankInSchoolFragment
            vm = this.vm
            adapter = RankAdapter(onRankItemClick)
        }
        subscribeObserver()
        performAction()
    }

    private fun subscribeObserver() {
        vm.ranks.observe(viewLifecycleOwner) {
            binding.adapter?.submitList("서울고 랭킹 TOP 10", it)
        }

        vm.school.observe(viewLifecycleOwner) {
            binding.rankInSchoolProfileName.text = it.name
            binding.rankInSchoolValue.text = it.rank
            binding.rankInSchoolProfileScore.text = it.score
            val arrowResource = if(it.isRankUp) R.drawable.ic_rank_up_arrow else R.drawable.ic_rank_down_arrow
            binding.rankInSchoolArrow.setImageDrawable(ContextCompat.getDrawable(requireContext(), arrowResource))
        }
    }

    private fun performAction() {
        vm.loadRank()
        vm.loadSchool()
    }

    fun onBackClick(view: View) {
        view.findNavController().navigateUp()
    }

    private val onRankItemClick = { view: View, rankItem: RankItem ->
    }
}