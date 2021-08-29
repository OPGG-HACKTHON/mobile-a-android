package com.opgg.chai.ui.auth.join.form

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.opgg.chai.model.data.auth.UserInfo
import com.opgg.chai.model.remote.AuthService
import com.opgg.chai.util.UserUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class JoinFormViewModel constructor(context: Context, private val authService: AuthService) : ViewModel() {
    lateinit var navController: NavController

    private val _emailAddress = MutableLiveData<String>()
    val lolName = MutableLiveData<String>()
    val emailAddress: LiveData<String> = _emailAddress
    val schoolName: MutableLiveData<String> = MutableLiveData<String>()
    var schoolId: String? = null

    lateinit var accessToken: String // todo: 대체될 수 있음

    init {
        val alreadySign = GoogleSignIn.getLastSignedInAccount(context)
        alreadySign?.let {
            _emailAddress.value = it.email
            accessToken = it.idToken
        }
    }

    fun moveBackPage() {
        navController.popBackStack()
    }

    fun isAllContainValue(): Boolean {
        var result = true

        schoolId ?: run { result = false }
        emailAddress.value ?: run { result = false }
        lolName.value ?: run {result = false }

        return result
    }

    fun signupUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val user: UserInfo? = authService.signupUser(
                        "google",
                        _emailAddress.value!!,
                        lolName.value!!,
                        schoolId!!,
                        accessToken
                    )

                    UserUtils.userInfo = user

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}