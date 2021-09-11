package com.opgg.chai.model.repository

import com.opgg.chai.model.data.response.Champion
import com.opgg.chai.model.data.response.CompareCategory
import com.opgg.chai.model.remote.ApiService

class LoLRepository(
    private val apiService: ApiService): Repository {

    private lateinit var champions: List<Champion>
    private lateinit var compareCategories: List<CompareCategory>

    suspend fun getChampions(): List<Champion> {
        if(!::champions.isInitialized) {
            champions = apiService.loadChampions()
        }
        return champions
    }

    suspend fun getCompareCategory(): List<CompareCategory> {
        if(!::compareCategories.isInitialized) {
            compareCategories = apiService.loadCompareCategory()
        }
        return compareCategories
    }


}