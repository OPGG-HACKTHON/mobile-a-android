package com.opgg.chai.ui.main.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.opgg.chai.R
import com.opgg.chai.ui.main.rank.adapters.RankAdapter
import com.opgg.chai.databinding.FragmentRankBinding
import com.opgg.chai.model.data.RankItem
import dagger.hilt.android.AndroidEntryPoint

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
            adapter = RankAdapter().apply {
                setListener(onRankItemClick)
            }
        }

        val viewType = arguments?.get("viewType") as Int? ?: 0
        vm.viewType = viewType
        subscribeObserver()
    }

    fun onChampionRankClick(view: View) {
        view.findNavController().navigate(R.id.action_rankFragment_to_rankChampionChoiceFragment)
    }


    private fun subscribeObserver() {
        with(vm) {
            rank.observe(viewLifecycleOwner) {
                binding.adapter?.submitList(it)
            }

            progress.observe(viewLifecycleOwner) {
                binding.progress.visibility = if(it) View.VISIBLE else View.GONE
            }
        }
    }

    private val onRankItemClick = { view: View, rankItem: RankItem ->
        if(vm.viewType != 0) {
            view.findNavController().navigate(
                R.id.action_rankFragment_to_rankInSchoolFragment,
                bundleOf("rankItem" to rankItem)
            )
        }
    }
}