package com.opgg.chai.model.remote

import com.opgg.chai.model.data.auth.Title
import com.opgg.chai.model.data.title.TitleHistoryItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface SettingService {

    @GET("/titles/users/{id}")
    suspend fun getUserTitleList(@Path("id") id: Int): List<Title>

    @GET("/titles/users/{id}/logs")
    suspend fun getUserTitleHistory(@Path("id") id: Int): List<TitleHistoryItem>

    @PUT("/titles/users/{id}")
    suspend fun setUserTitle(@Path("id") userId: Int, @Body titleId: Map<String, Int>) : Response<Unit>
}