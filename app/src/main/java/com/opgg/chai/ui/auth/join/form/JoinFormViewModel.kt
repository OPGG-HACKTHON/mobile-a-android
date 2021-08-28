package com.opgg.chai.ui.auth.join.form

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn

class JoinFormViewModel constructor(context: Context) : ViewModel() {
    private val _emailAddress = MutableLiveData<String>()
    var lolName = MutableLiveData<String>()
    val emailAddress: LiveData<String> = _emailAddress

    init {
        val alreadySign = GoogleSignIn.getLastSignedInAccount(context)
        alreadySign?.let {
            _emailAddress.value = it.email
        }
    }
}