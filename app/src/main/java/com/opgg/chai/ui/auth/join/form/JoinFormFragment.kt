package com.opgg.chai.ui.auth.join.form

import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentJoinFormBinding
import com.opgg.chai.ui.base.BaseFragment

class JoinFormFragment : BaseFragment<FragmentJoinFormBinding, JoinFormViewModel>() {
    override var viewModel: JoinFormViewModel
        get() = JoinFormViewModel()
        set(value) {}
    override val layoutRes: Int
        get() = R.layout.fragment_join_form
}