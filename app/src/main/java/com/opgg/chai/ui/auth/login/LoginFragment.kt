package com.opgg.chai.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentLoginBinding
import com.opgg.chai.ui.base.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override lateinit var viewModel: LoginViewModel
    override val layoutRes: Int = R.layout.fragment_login

    private val googleSignClient: GoogleSignInClient by lazy {
        //사용자 데이터 요청
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(resources.getString(R.string.server_client_id))
            .requestEmail() //이메일 정보 요청
            .build()

        GoogleSignIn.getClient(requireContext(), gso)
    }

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
        viewModel = LoginViewModel(requireContext())
        viewModel.navController = navController

        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.loginGoogleButton.setOnClickListener { googleSignClient.signInIntent.apply{ loginWithGoogle.launch(this)} }
        return view
    }
}