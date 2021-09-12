package com.opgg.chai.model.remote

import com.opgg.chai.model.data.rank.RankInSchoolData
import com.opgg.chai.model.data.response.Champion
import com.opgg.chai.model.data.response.CompareCategory
import com.opgg.chai.model.data.response.School
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/lol/champions")
    suspend fun loadChampions(): List<Champion>

    @GET("/lol/compareFields")
    suspend fun loadCompareCategory(): List<CompareCategory>

    // ** 개인 랭킹 **//
    @GET("/ranks/schools/{id}")
    suspend fun getRanksInSchool(
        @Path("id") schoolId: String
    ): List<RankInSchoolData>

    // ** 학교 내의 나의 랭킹(userId에 나의 아이디를 넣어야함) **//
    @GET("/ranks/schools/{schoolId}/users/{userId}")
    suspend fun getRankByUserId(
        @Path("schoolId") schoolId: String, @Path("userId") userId: Int
    ): RankInSchoolData

    // ** 학교 간의 랭킹 ** //
    @GET("/ranks/regions/{id}")
    suspend fun getSchoolRanks(
        @Path("id") regionId:String): List<School>

    // ** 나의 학교 랭킹(나의 학교 ID를 파라미터에 넣어야함) ** //
    @GET("/ranks/regions/{regionId}/schools/{schoolId}")
    suspend fun getSchoolRankBySchoolId(
        @Path("regionId") regionId:String, @Path("schoolId") schoolId: String): School

    // ** 챔피언 실력 비교 랭킹 **//
    @GET("/ranks/champions/{championId}/compareFields/{compareFieldId}/schools/{schoolId}")
    suspend fun getChampionRank(
        @Path("championId") championId: String,
        @Path("compareFieldId") compareFieldId: String,
        @Path("schoolId") schoolId: String): List<RankInSchoolData>


    // ** 나의 챔피언 실력 비교 랭킹(나의 ID를 파라미터에 넣어야함) **//
    @GET("/ranks/champions/{championId}/compareFields/{compareFieldId}/schools/{schoolId}/users/{userId}")
    suspend fun getChampionRankByUserId(
        @Path("championId") championId: String,
        @Path("compareFieldId") compareFieldId: String,
        @Path("schoolId") schoolId: String,
        @Path("userId") userId: String): RankInSchoolData


    // ** 유저 프로필 정보 **//
    @GET("/users/{id}/profile")
    suspend fun getProfileBy(
        @Path("id") userId: String): RankInSchoolData

    // ** 학교 id로 검색 ** /
    @GET("schools/{id}")
    suspend fun getSchoolBy(
        @Path("id") schoolId: String): School

}