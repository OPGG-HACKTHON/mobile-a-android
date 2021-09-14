package com.opgg.chai.ui.main.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.model.remote.ApiService
import com.opgg.chai.model.repository.RegionRepository
import com.opgg.chai.util.UserUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
    private val regionRepository: RegionRepository,
    application: Application
): AndroidViewModel(application) {

    private var _rankInSchool = MutableLiveData<RankItem>()
    val rankInSchool: LiveData<RankItem>
        get() = _rankInSchool

    private var _schoolRankInRegion = MutableLiveData<RankItem>()
    val schoolRankInRegion: LiveData<RankItem>
        get() = _schoolRankInRegion

    private var _myProfile = MutableLiveData<RankItem>()
    val myProfile: LiveData<RankItem>
        get() = _myProfile

    private var _myRegionName = MutableLiveData<String>()
    val myRegionName: LiveData<String>
        get() = _myRegionName

    init {
        loadMyProfile()
        loadRankInSchool()
        loadSchoolRankInRegion()
    }


    fun loadMyProfile() = viewModelScope.launch {
        UserUtils.userInfo?.let {
            try {
                val response = apiService.getProfileBy(it.id.toString())

                _myProfile.value = response.parserRankItem()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun loadRankInSchool() = viewModelScope.launch {
        UserUtils.userInfo?.let {
            if(it.school?.id != null && it.id != null) {
                if(it.school.regionId != null) {
                    loadRegionName(it.school.regionId)
                }
                val myRankResponse =
                    apiService.getRankByUserId(it.school.id, it.id)
                _rankInSchool.value = myRankResponse.parserRankItem(me = true)
            }
        }
    }

    private suspend fun loadRegionName(id: Int) {
        val regionName = regionRepository.getRegionNameBy(id)
        _myRegionName.value = regionName
    }

    fun loadSchoolRankInRegion() = viewModelScope.launch {
        UserUtils.userInfo?.let { userInfo ->
            try {
                val myRankResponse =
                    apiService.getSchoolRankBySchoolId("1", userInfo?.school?.name ?: "")
                _schoolRankInRegion.value = myRankResponse.parserRankItem(me = true)
            }catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}