package com.opgg.chai.model.repository

import com.opgg.chai.model.data.battle.BattleUser
import com.opgg.chai.model.remote.BattleService

class BattleRepository(val battleService: BattleService): Repository {
    private val searchBattleUserList = ArrayList<BattleUser?>()
    var battleUser: BattleUser? = null

    private val prevBattleUserList = ArrayList<BattleUser>()

    fun getPrevBattleUser(): List<BattleUser> {
        return prevBattleUserList
    }

    suspend fun getBattleUserList(userName: String): List<BattleUser?> {
        val result = battleService.searchForBattleUser(userName)
        if(!searchBattleUserList.contains(result)) {
            searchBattleUserList.clear()
            searchBattleUserList.addAll(result)
        }

        return searchBattleUserList
    }

    fun setUser(_battleUser: BattleUser) {
        battleUser = _battleUser
        addPrevBattleUserList(_battleUser)
    }

    fun addPrevBattleUserList(user: BattleUser) {
        if(prevBattleUserList.size > 2) {
            prevBattleUserList.removeAt(0)
        }

        if(!prevBattleUserList.contains(user)) prevBattleUserList.add(user)
    }

    fun savePrevBattleUserList() {

    }
}
