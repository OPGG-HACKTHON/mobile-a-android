package com.opgg.chai.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        performAction()
        return view
    }

    private fun performAction() {
        viewModel.loadRank()
    }
}