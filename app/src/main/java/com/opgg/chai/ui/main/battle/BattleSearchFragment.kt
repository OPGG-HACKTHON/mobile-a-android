package com.opgg.chai.ui.main.battle

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentBattleSearchBinding
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BattleSearchFragment : BaseFragment<FragmentBattleSearchBinding, BattleSearchViewModel>() {
    @Inject
    lateinit override var viewModel: BattleSearchViewModel
    override val layoutRes: Int = R.layout.fragment_battle_search

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initLayout()

        return view
    }

    fun initLayout() {
        binding.view = this
        binding.viewModel = viewModel

        catchEnterCode()
    }

    fun catchEnterCode() {
        binding.battleSearch.setOnKeyListener { v, keyCode, event ->
            if (keyCode.equals(KeyEvent.KEYCODE_ENTER)) {
                viewModel.searchChaiMember(binding.battleSearch.text.toString())
                binding.battleSearch.setText("")
            }

            true
        }
    }

    fun moveBack() {
        navController.popBackStack()
    }

    fun shareChai() {
        startActivity(viewModel.shareChai())
    }
}
