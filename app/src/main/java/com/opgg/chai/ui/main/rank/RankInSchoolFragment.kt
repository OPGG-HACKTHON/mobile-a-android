package com.opgg.chai.ui.main.rank

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.opgg.chai.R
import com.opgg.chai.ui.main.rank.adapters.RankAdapter
import com.opgg.chai.databinding.FragmentRankInSchoolBinding
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.util.extension.loadRoundedCornerImage
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
            adapter = RankAdapter().apply {
                setListener(onRankItemClick)
            }
        }

        val school = arguments?.getParcelable<RankItem>("rankItem")
        school?.let {
            binding.rankInSchoolProfileImage.loadRoundedCornerImage(it.image)
            binding.rankInSchoolProfileName.text = it.name
            binding.rankInSchoolValue.text = it.rank
            binding.rankInSchoolProfileScore.text = it.score
            binding.rankInSchoolArrow.setImageDrawable(ContextCompat.getDrawable(requireContext(),  it.getRankArrowImage()))
        }

        vm.school = school
        subscribeObserver()
        performAction()
    }

    private fun subscribeObserver() {
        vm.ranks.observe(viewLifecycleOwner) {
            binding.adapter?.submitList(it)
        }

        vm.progress.observe(viewLifecycleOwner) {
            binding.progress.visibility = if(it) View.VISIBLE else View.GONE
        }

        vm.title.observe(viewLifecycleOwner) {
//            binding.adapter?.setHeaderMessage(it)
        }
    }

    private fun performAction() {
        vm.loadRank()
    }

    fun onBackClick(view: View) {
        view.findNavController().navigateUp()
    }

    private val onRankItemClick = { view: View, rankItem: RankItem ->
    }
}