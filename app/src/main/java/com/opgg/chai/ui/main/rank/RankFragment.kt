package com.opgg.chai.ui.main.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.opgg.chai.R
import com.opgg.chai.adapters.RankAdapter
import com.opgg.chai.databinding.FragmentRankBinding
import com.opgg.chai.model.data.RankItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.view.*

@AndroidEntryPoint
class RankFragment : Fragment() {

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
            adapter = RankAdapter(onRankItemClick)
        }
        subscribeObserver()
        performAction()
    }

    private fun subscribeObserver() {
        with(vm) {
            rank.observe(viewLifecycleOwner) {
                binding.adapter?.submitList("서울고에서 나의 랭킹", it)
            }
        }
    }

    private fun performAction() {
        vm.loadRank()
    }

    private val onRankItemClick = { view: View, rankItem: RankItem ->
        view.findNavController().navigate(R.id.action_homeFragment_to_rankInSchoolFragment)
    }
}