package com.opgg.chai.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentLoginBinding
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    @Inject override lateinit var viewModel: LoginViewModel
    override val layoutRes: Int = R.layout.fragment_login

    private val loginWithGoogle = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        viewModel.handleGoogleLogin(task)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        viewModel.navController = navController

        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.loginGoogleButton.setOnClickListener { viewModel.googleClient.signInIntent.apply{ loginWithGoogle.launch(this)} }

        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkIsAlreadySigned()
    }
}