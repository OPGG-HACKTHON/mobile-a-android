package com.opgg.chai.ui.auth.login

import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentLoginBinding
import com.opgg.chai.ui.base.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override var viewModel: LoginViewModel = LoginViewModel()
    override val layoutRes: Int = R.layout.fragment_login

}