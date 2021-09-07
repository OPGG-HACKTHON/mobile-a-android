package com.opgg.chai.ui.main.battle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentBattleResultBinding
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BattleResultFragment : BaseFragment<FragmentBattleResultBinding, BattleResultViewModel>() {
    @Inject
    override lateinit var viewModel: BattleResultViewModel
    override val layoutRes: Int = R.layout.fragment_battle_result

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

        arguments?.let {
            viewModel.resultTitle.value = it.getString("title")
        }
    }

    fun moveBack(view: View) {
        navController.popBackStack()
    }
}
