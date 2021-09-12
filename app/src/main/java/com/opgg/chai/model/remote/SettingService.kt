package com.opgg.chai.model.remote

import com.opgg.chai.model.data.title.TitleHistoryItem
import com.opgg.chai.model.data.title.TitleItem
import retrofit2.http.GET
import retrofit2.http.Path

interface SettingService {

    @GET("/titles/users/{id}")
    suspend fun getUserTitleList(@Path("id") id: Int): List<TitleItem>

    @GET("/titles/users/{id}/logs")
    suspend fun getUserTitleHistory(@Path("id") id: Int): List<TitleHistoryItem>
}