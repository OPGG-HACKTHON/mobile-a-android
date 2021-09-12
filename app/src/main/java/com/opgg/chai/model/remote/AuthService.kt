package com.opgg.chai.model.remote

import com.opgg.chai.model.data.auth.LoginResult
import com.opgg.chai.model.data.auth.SchoolInfo
import com.opgg.chai.model.data.auth.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("/auth/login")
    suspend fun isOurUser(@Body authData: Map<String, String>): LoginResult?


    // 사용자 가입 api
    @POST("/auth/signup")
    suspend fun signupUser(@Body authData: Map<String, String>): User?

    //사용자 정보 조회
    @POST("/auth/validate?")
    suspend fun getUserInfo(@Query("token") token: String): User

    //학교 조회 api
    @GET("/schools/search")
    suspend fun searchSchool(
        @Query("division") division: String,
        @Query("searchWord") searchWord: String
    ): List<SchoolInfo>?
}