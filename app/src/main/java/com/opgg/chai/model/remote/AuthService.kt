package com.opgg.chai.model.remote

import com.opgg.chai.model.data.auth.LoginResult
import com.opgg.chai.model.data.auth.SchoolInfo
import com.opgg.chai.model.data.auth.UserInfo
import retrofit2.http.*

interface AuthService {

    @FormUrlEncoded
    @POST("/auth/login")
    suspend fun isOurUser(
        @Field("authFrom") authFrom: String,
        @Field("accesstoken") accesstoken: String
    ): LoginResult?


    // 사용자 가입 api
    @FormUrlEncoded
    @POST("/auth/signup")
    suspend fun signupUser(
        @Field("authForm") authFrom: String,
        @Field("email") email: String,
        @Field("LOLNickName") lolNickName: String,
        @Field("schoolId") schoolId: String,
        @Field("accesstoken") accesstoken: String
    ): UserInfo?

    //사용자 정보 조회
    @FormUrlEncoded
    @POST("/auth/validate")
    suspend fun getUserInfo(
        @Field("accessToken") accessToken: String
    ): UserInfo

    //학교 조회 api
    @GET("/schools/search")
    suspend fun searchSchool(
        @Query("division") division: String,
        @Query("searchWord") searchWord: String
    ): List<SchoolInfo>?
}