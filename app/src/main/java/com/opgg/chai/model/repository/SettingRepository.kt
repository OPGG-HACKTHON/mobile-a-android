package com.opgg.chai.model.repository

import com.opgg.chai.model.data.title.TitleHistoryItem
import com.opgg.chai.model.data.auth.Title
import com.opgg.chai.model.remote.SettingService

class SettingRepository(val settingService: SettingService): Repository {
    val titleList = ArrayList<Title>()
    val titleHistory = ArrayList<TitleHistoryItem>()
    // 사용자 타이틀 리스트 가져오기
    suspend fun getUserTitle(id: Int): List<Title> {
        if(titleList.size < 1) {
            val result = settingService.getUserTitleList(id)

            titleList.clear()
            titleList.addAll(result)
        }

        return titleList
    }

    // 사용자 타이틀 히스토리 가져오기
    suspend fun getUserTitleHistory(id: Int): List<TitleHistoryItem> {
        if(titleHistory.size < 1) {
            val result = settingService.getUserTitleHistory(id)

            titleHistory.clear()
            titleHistory.addAll(result)
        }

        return titleHistory
    }

    suspend fun setUserTitle(id: Int, title: Map<String, Int>) {
        settingService.setUserTitle(id, title)
    }
}