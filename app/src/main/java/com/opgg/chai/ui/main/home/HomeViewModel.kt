package com.opgg.chai.ui.main.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.model.remote.ApiService
import com.opgg.chai.util.UserUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
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

    fun loadRankInSchool() = viewModelScope.launch {
        UserUtils.userInfo?.let { userInfo ->
            try {
                val myRankResponse =
                    apiService.getRankByUserId(userInfo?.school?.name ?: "", userInfo?.id ?: 1)
                _rankInSchool.value = myRankResponse.parserRankItem(me = true)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
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