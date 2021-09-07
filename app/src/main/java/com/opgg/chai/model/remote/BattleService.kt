package com.opgg.chai.model.remote

import com.opgg.chai.model.data.battle.BattleUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BattleService {

    @GET("/battles/search/{lolNickname}")
    suspend fun searchForBattleUser(@Path("lolNickname") lolNickname: String): List<BattleUser?>

    @POST("/battles/message/send")
    suspend fun sendMessage(@Body sendBattle: Map<String, String>)
}
