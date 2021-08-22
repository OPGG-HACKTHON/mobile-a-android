package com.opgg.chai.ui.main.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.opgg.chai.R
import com.opgg.chai.adapters.RankAdapter
import com.opgg.chai.databinding.FragmentRankBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankFragment : Fragment(R.layout.fragment_rank) {

    private val vm: RankViewModel by viewModels()
    private lateinit var binding: FragmentRankBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rank, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fragment = this@RankFragment
            vm = this@RankFragment.vm
            adapter = RankAdapter()
        }
        subscribeObserver()
        performAction()
    }

    private fun subscribeObserver() {
        with(vm) {
            rank.observe(viewLifecycleOwner) {
                binding.adapter?.submitList("Title", it)
            }
        }
    }

    private fun performAction() {
        vm.loadRank()
    }
}