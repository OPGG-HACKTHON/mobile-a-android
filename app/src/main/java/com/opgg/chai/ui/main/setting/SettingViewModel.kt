package com.opgg.chai.ui.main.setting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.opgg.chai.util.UserUtils
import javax.inject.Inject

class SettingViewModel @Inject constructor(val googleSignInClient: GoogleSignInClient?): ViewModel() {
    val userEmail = UserUtils.userEmail
    var action: String = ""

    private val _isMoveHome = MutableLiveData<Boolean>(false)
    val isMoveHome: LiveData<Boolean> = _isMoveHome

    private fun logout() {
        googleSignInClient?.signOut()
        _isMoveHome.value = true
    }

    private fun leave() {
        _isMoveHome.value = true
    }

    fun getDialogResult() {
        Log.d("result action", "$action")

        when(action) {
            "logout" -> logout()
            "leave" -> leave()
        }
    }
}