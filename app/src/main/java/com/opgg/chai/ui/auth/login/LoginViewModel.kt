package com.opgg.chai.ui.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.opgg.chai.R
import com.opgg.chai.model.remote.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val googleClient: GoogleSignInClient,
    val authService: AuthService
) : ViewModel() {
    lateinit var navController: NavController

    fun checkIsAlreadySigned() {
        val account = GoogleSignIn.getLastSignedInAccount(googleClient.applicationContext)
        account?.let {
            login(account)
        }
    }

    fun handleGoogleLogin(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.result
            Log.d("login result", "${account.idToken}")
            // 첫 로그인 가입 화면
            login(account)

        } catch (e: ApiException) {
            Log.d("login result", "${e.statusCode} : ${e.status}")
            e.printStackTrace()
        }
    }

    fun login(account: GoogleSignInAccount) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = authService.isOurUser("google", account.idToken)
                    Log.d("login result", "${result?.message}")

                    result?.let {
                        movePage(R.id.action_loginFragment_to_homeFragment)
                    }
                } catch (e: Exception) {
                    Log.d("error", "${e.message}")
                    e.localizedMessage?.let {
                        if (it.contains("500")) {
                            //todo: 추후 사용할 예정
//                            googleClient.revokeAccess()
                        } else {
                            movePage(R.id.action_loginFragment_to_joinTermsFragment)
                        }
                        //todo: remove
                        movePage(R.id.action_loginFragment_to_joinTermsFragment)
                    }
                }
            }
        }
    }

    private suspend fun movePage(action: Int) {
        withContext(Dispatchers.Main) {
            navController.navigate(action)
        }
    }
}
