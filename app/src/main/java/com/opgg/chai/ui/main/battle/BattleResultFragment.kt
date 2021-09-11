package com.opgg.chai.ui.main.battle

import android.os.Bundle
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
    lateinit override var viewModel: BattleResultViewModel
    override val layoutRes: Int = R.layout.fragment_battle_result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battle_result, container, false)
    }
}