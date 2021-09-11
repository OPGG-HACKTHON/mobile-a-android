package com.opgg.chai.model.remote

import com.opgg.chai.model.data.rank.RankInSchoolData
import com.opgg.chai.model.data.response.Champion
import com.opgg.chai.model.data.response.CompareCategory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/lol/champions")
    suspend fun loadChampions(): List<Champion>

    @GET("/lol/compareFields")
    suspend fun loadCompareCategory(): List<CompareCategory>

    // ** 학교 내의 개인 랭킹 **//
    @GET("/ranks/schools/{id}")
    suspend fun getRanksInSchool(
        @Path("id") schoolId: String
    ): List<RankInSchoolData>

}