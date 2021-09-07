package com.opgg.chai.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.opgg.chai.model.remote.AuthService
import com.opgg.chai.ui.auth.join.form.JoinFormViewModel
import com.opgg.chai.ui.auth.join.search.JoinSearchViewModel
import com.opgg.chai.ui.auth.login.LoginViewModel
import com.opgg.chai.util.GoogleUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object AuthModule {
    @Provides
    fun provideGoogleClient(@ApplicationContext context: Context) =
        GoogleUtils.getGoogleSignClient(context)
}
