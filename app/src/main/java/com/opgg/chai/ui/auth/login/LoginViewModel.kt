package com.opgg.chai.ui.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.opgg.chai.R
import com.opgg.chai.model.remote.AuthService
import com.opgg.chai.util.SharedPreferenceUtil
import com.opgg.chai.util.UserUtils
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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                SharedPreferenceUtil.getValue(SharedPreferenceUtil.accessTokenKey)?.let { token ->
                    if (!token.isNullOrEmpty()) getValidateUserInfo(token)
                    else googleClient.signOut()
                }
            }
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
                    val autData = HashMap<String, String>().apply {
                        put("authFrom", "google")
                        put("accesstoken", account.idToken!!)
                    }
                    val result = authService.isOurUser(autData)
                    result?.message?.let {
                        SharedPreferenceUtil.addValue(
                            SharedPreferenceUtil.accessTokenKey,
                            result.accessToken
                        ) // 토큰 저장

                        if (it.contains("유저 정보가 없습니다. 회원가입을 진행합니다.")) {
                            SharedPreferenceUtil.addValue(SharedPreferenceUtil.accessTokenKey, result.accessToken)
                            movePage(R.id.action_loginFragment_to_joinTermsFragment)
                        } else {
                            SharedPreferenceUtil.addValue(
                                SharedPreferenceUtil.emailKey,
                                account.email ?: ""
                            ) // 이메일 저장
                            getValidateUserInfo(result.accessToken)
                        }
                    }
                } catch (e: Exception) {
                    Log.d("login", "${e.message} revoke")
                    googleClient.revokeAccess()
                }
            }
        }
    }

    private suspend fun movePage(action: Int) {
        withContext(Dispatchers.Main) {
            navController.navigate(action)
        }
    }

    private suspend fun getValidateUserInfo(token: String) {
        try {
            UserUtils.userInfo = authService.getUserInfo(token)
            UserUtils.userEmail = SharedPreferenceUtil.getValue(SharedPreferenceUtil.emailKey)

            if (UserUtils.userEmail.isNullOrEmpty()) return //이메일 정보가 없으면 가입을 완료하지 않은 것
            movePage(R.id.action_loginFragment_to_homeFragment)
        } catch (e: java.lang.Exception) {
            // 사용자 정보 삭제
            SharedPreferenceUtil.removeValue(SharedPreferenceUtil.accessTokenKey)
            SharedPreferenceUtil.removeValue(SharedPreferenceUtil.emailKey)
            googleClient.revokeAccess()
        }
    }


}
