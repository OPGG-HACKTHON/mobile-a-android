package com.opgg.chai.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentHomeBinding
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    @Inject override lateinit var viewModel: HomeViewModel
    override val layoutRes: Int = R.layout.fragment_home


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.myProfile.observe(viewLifecycleOwner) {
            binding.homeProfileSummonerLevel.visibility = if(it.summonerLevel.isEmpty()) View.INVISIBLE else View.VISIBLE
        }

        binding.homeMyDetail.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_rankFragment,
                bundleOf("viewType" to 0)
            )
        }

        binding.homeSchoolDetail.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_rankFragment,
                bundleOf("viewType" to 1)
            )
        }

        binding.championRank.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_titleFragment)
        }

        return view
    }
}