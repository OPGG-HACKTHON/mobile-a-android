package com.opgg.chai.ui.main.setting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.opgg.chai.util.SharedPreferenceUtil
import com.opgg.chai.util.UserUtils
import javax.inject.Inject

class SettingViewModel @Inject constructor(val googleSignInClient: GoogleSignInClient?): ViewModel() {
    val userEmail = UserUtils.userEmail
    var action: String = ""

    private val _isMoveHome = MutableLiveData<Boolean>(false)
    val isMoveHome: LiveData<Boolean> = _isMoveHome

    private fun logout() {
        googleSignInClient?.signOut()
        clearUserInfo()
        _isMoveHome.value = true
    }

    private fun leave() {
        logout()
    }

    fun getDialogResult() {
        Log.d("result action", "$action")

        when(action) {
            "logout" -> logout()
            "leave" -> leave()
        }
    }

    // 사용자 토큰과 이메일 정보를 초기화합니다.
    private fun clearUserInfo() {
        SharedPreferenceUtil.apply {
            removeValue(accessTokenKey)
            removeValue(emailKey)
        }
    }
}