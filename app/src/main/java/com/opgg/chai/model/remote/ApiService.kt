package com.opgg.chai.model.remote

import com.opgg.chai.model.data.rank.RankInSchoolData
import retrofit2.http.*

interface ApiService {

    // ** 학교 내의 개인 랭킹 **//
    @GET("/ranks/schools/{id}")
    suspend fun getRanksInSchool(
        @Path("id") schoolId: String
    ): List<RankInSchoolData>


}