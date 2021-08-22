package com.opgg.chai.ui.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.opgg.chai.R
import javax.inject.Inject

class LoginViewModel @Inject constructor(val googleClient: GoogleSignInClient) : ViewModel() {
    lateinit var navController: NavController

    fun handleGoogleLogin(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.result
            Log.d("login result", "${account.email} ${account.displayName}")

            // todo : 가입 여부 확인 (차이 서버)
            // 첫 로그인 가입 화면
            navController.navigate(R.id.action_loginFragment_to_joinTermsFragment)
        } catch (e: ApiException) {
            Log.d("login result", "${e.statusCode} : ${e.status}")
            e.printStackTrace()
        }
    }
}