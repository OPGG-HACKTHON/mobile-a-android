package com.opgg.chai.model.repository

import com.opgg.chai.model.remote.ApiService

class RegionRepository(private val apiService: ApiService): Repository {

    suspend fun getRegionNameBy(id: Int): String {
        val response = apiService.getRegionBy(id)
        return response.name
    }

}