package com.opgg.chai.di

import com.opgg.chai.model.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideWebService(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("domain")
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

}