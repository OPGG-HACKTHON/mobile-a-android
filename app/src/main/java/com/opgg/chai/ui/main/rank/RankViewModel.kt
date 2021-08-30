package com.opgg.chai.ui.main.rank

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.model.remote.ApiService
import com.opgg.chai.util.UserUtils
import kotlinx.coroutines.launch

class RankViewModel @ViewModelInject constructor(
    private val apiService: ApiService,
    application: Application):
    AndroidViewModel(application) {

    var viewType = 0  // 0 - 개인랭킹, 1 - 학교랭킹
        set(value) {
            field = value
            if(field == 0) {
                loadRankInSchool()
            } else {
                loadSchoolRank()
            }
        }

    private var _rank = MutableLiveData<List<RankItem>>()
    val rank: MutableLiveData<List<RankItem>>
        get() = _rank

    init {
        viewType = 0
    }

    private fun loadSchoolRank() = viewModelScope.launch {
        // todo 학교랭킹 API 추가시 구현
        _rank.value = emptyList()
    }

    private fun loadRankInSchool() = viewModelScope.launch {
        UserUtils.userInfo?.let {
            val response = apiService.getRanksInSchool(it.schoolId)

            val items = response.map { rankInSchoolData ->
                rankInSchoolData.parserRankItem()
            }.toList()

            _rank.value = items
        }
//        val response = apiService.getRanksInSchool("B000011965")
//
//        val items = response.map { rankInSchoolData ->
//            rankInSchoolData.parserRankItem()
//        }.toList()
//
//        _rank.value = items
    }


}