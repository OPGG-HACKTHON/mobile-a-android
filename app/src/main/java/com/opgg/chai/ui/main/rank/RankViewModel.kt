package com.opgg.chai.ui.main.rank

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.opgg.chai.ChiApplication
import com.opgg.chai.R
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
    val rank: LiveData<List<RankItem>>
        get() = _rank

    private var _progress = MutableLiveData(false)
    val progress: LiveData<Boolean>
        get() = _progress

    init {
        viewType = 0
    }

    private fun loadSchoolRank() = viewModelScope.launch {
        UserUtils.userInfo?.let { userInfo ->
            _progress.value = true
            val response = apiService.getSchoolRanks("1")
            val myRankResponse = apiService.getSchoolRankBySchoolId("1", userInfo?.school?.id ?: "1")

            val items = response
                .map { rankInSchoolData ->
                rankInSchoolData.parserRankItem()
            }.toMutableList()
            items.add(0, myRankResponse.parserRankItem(me = true))
            items.add(0, RankItem(viewType = "HEADER", title = "랭킹"))
            items.add(items.size, RankItem(viewType = "FOOTER"))
            _rank.value = items
            _progress.value = false
        }
    }

    private fun loadRankInSchool() = viewModelScope.launch {
        UserUtils.userInfo?.let {
            if(it.school?.id != null && it.id != null) {
                _progress.value = true
                val response = apiService.getRanksInSchool(it.school.id)
                val myRankResponse = apiService.getRankByUserId(it.school.id, it.id)

                val items = response
                    .filter { rankInSchoolData -> rankInSchoolData.id != it.id }
                    .map { rankInSchoolData ->
                        rankInSchoolData.parserRankItem()
                    }.toMutableList()
                items.add(0, myRankResponse.parserRankItem(me = true))
                items.add(0, RankItem(viewType = "HEADER", title = getApplication<ChiApplication>().resources.getString(
                    R.string.rank_my_rank_in_school, it.school.name)))
                items.add(items.size, RankItem(viewType = "FOOTER"))
                _rank.value = items
                _progress.value = false
            }
        }
    }


}