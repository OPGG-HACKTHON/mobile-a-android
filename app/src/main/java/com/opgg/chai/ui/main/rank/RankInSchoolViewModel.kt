package com.opgg.chai.ui.main.rank

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

class RankInSchoolViewModel @ViewModelInject constructor(
    private val apiService: ApiService,
    application: Application):
    AndroidViewModel(application) {

    var school: RankItem? = null

    private var _ranks = MutableLiveData<List<RankItem>>()
    val ranks: MutableLiveData<List<RankItem>>
        get() = _ranks

    private var _progress = MutableLiveData(false)
    val progress: LiveData<Boolean>
        get() = _progress


    fun loadRank() = viewModelScope.launch {
        school?.let {
            _progress.value = true
            val response = apiService.getRanksInSchool(it.id.toString())
            val items = response
                .map { rankInSchoolData ->
                    rankInSchoolData.parserRankItem()
                }.toList()
            _ranks.value = items
            _progress.value = false
        }
    }
}