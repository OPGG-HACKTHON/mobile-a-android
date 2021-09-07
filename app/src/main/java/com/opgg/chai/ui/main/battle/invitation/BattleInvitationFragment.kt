package com.opgg.chai.ui.main.battle.invitation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentBattleInvitationBinding
import com.opgg.chai.model.data.battle.BattleUser
import com.opgg.chai.model.data.battle.Lol
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BattleInvitationFragment : BaseFragment<FragmentBattleInvitationBinding, InvitationViewModel>() {
    @Inject override lateinit var viewModel: InvitationViewModel
    override val layoutRes: Int = R.layout.fragment_battle_invitation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)
        initLayout()

        return view
    }

    private fun initLayout() {
        binding.view = this
        binding.viewModel = viewModel
    }

    fun moveBack(view: View) {
        navController.popBackStack()
    }
}
