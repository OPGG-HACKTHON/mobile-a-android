package com.opgg.chai.di

import com.opgg.chai.model.remote.ApiService
import com.opgg.chai.model.remote.SettingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideWebService(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://api.opggmobilea.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun okHttpClient() = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
    }.build()


    @Provides
    fun webService(retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)

    @Provides
    fun getSettingService(retrofit: Retrofit) = retrofit.create(SettingService::class.java)

}