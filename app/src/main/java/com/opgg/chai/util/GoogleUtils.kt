package com.opgg.chai.util

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.opgg.chai.R

object GoogleUtils {
    private var googleSignClient: GoogleSignInClient? = null

    fun getGoogleSignClient(context: Context): GoogleSignInClient {
        return (googleSignClient ?: run {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.resources.getString(R.string.server_client_id))
                .requestEmail() //이메일 정보 요청
                .build()
            //이메일 정보 요청
            googleSignClient = GoogleSignIn.getClient(context, gso)
            googleSignClient
        }) as GoogleSignInClient
    }
}
