package com.opgg.chai.model.remote

import com.opgg.chai.model.data.battle.BattleUser
import retrofit2.http.GET
import retrofit2.http.Path

interface BattleService {

    @GET("/battles/search/{lolNickname}")
    suspend fun searchForBattleUser(@Path("lolNickname") lolNickname: String): List<BattleUser?>
}
