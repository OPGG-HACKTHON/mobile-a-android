package com.opgg.chai.ui.main.battle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentBattleBinding
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BattleFragment : BaseFragment<FragmentBattleBinding, BattleViewModel>() {
    @Inject override lateinit var viewModel: BattleViewModel
    override val layoutRes: Int = R.layout.fragment_battle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initLayout()

        return view
    }

    private fun initLayout() {
        binding.view = this
        binding.viewModel = viewModel
    }

    fun moveBattleSearch() {
        navController.navigate(R.id.action_battleFragment_to_battleSearchFragment)
    }
}
