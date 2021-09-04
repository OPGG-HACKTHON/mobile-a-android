package com.opgg.chai.model.remote

import com.opgg.chai.model.data.response.Champion
import com.opgg.chai.model.data.response.CompareCategory
import retrofit2.http.GET

interface ApiService {

    @GET("/lol/champions")
    suspend fun loadChampions(): List<Champion>

    @GET("/lol/compareFields")
    suspend fun loadCompareCategory(): List<CompareCategory>

}