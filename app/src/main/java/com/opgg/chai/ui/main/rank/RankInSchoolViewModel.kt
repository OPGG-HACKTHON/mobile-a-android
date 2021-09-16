package com.opgg.chai.ui.main.rank

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.model.remote.ApiService
import kotlinx.coroutines.launch

class RankInSchoolViewModel @ViewModelInject constructor(
    private val apiService: ApiService,
    application: Application):
    AndroidViewModel(application) {

    companion object {
        const val RANK_COUNT = 10
    }

    var school: RankItem? = null

    private var _ranks = MutableLiveData<List<RankItem>>()
    val ranks: MutableLiveData<List<RankItem>>
        get() = _ranks

    private var _progress = MutableLiveData(false)
    val progress: LiveData<Boolean>
        get() = _progress

    private var _title = MutableLiveData("")
    val title: LiveData<String>
        get() = _title

    fun loadRank() = viewModelScope.launch {
        school?.let {
            _progress.value = true
            val seoulHighSchoolId = "B000012052" // todo 이전 값이 더미여서 임시 추가함. 서버개발완료시 변경 예정
            val response = apiService.getRanksInSchool(seoulHighSchoolId)
            val items = response
                .take(RANK_COUNT)
                .map { rankInSchoolData ->
                    rankInSchoolData.parserRankItem()
                }.toMutableList()

            items.add(0, RankItem(viewType = "HEADER", title = "${it.name} 랭킹 TOP $RANK_COUNT"))
            items.add(items.size, RankItem(viewType = "FOOTER"))
            _ranks.value = items
            _progress.value = false
        }
    }
}