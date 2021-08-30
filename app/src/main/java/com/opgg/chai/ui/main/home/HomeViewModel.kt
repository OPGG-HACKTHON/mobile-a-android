package com.opgg.chai.ui.main.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opgg.chai.model.data.RankItem
import javax.inject.Inject

class HomeViewModel @Inject constructor(application: Application): AndroidViewModel(application) {

    private var _rankInSchool = MutableLiveData<RankItem>()
    val rankInSchool: LiveData<RankItem>
        get() = _rankInSchool

    private var _schoolRankInRegion = MutableLiveData<RankItem>()
    val schoolRankInRegion: LiveData<RankItem>
        get() = _schoolRankInRegion


    fun loadRank() {
        _rankInSchool.value = RankItem(id = 0, name = "쪼렙이다말로하자", score = "Challenger 1,724 LP", isRankUp = true, rank = "23")
        _schoolRankInRegion.value = RankItem(id = 0, name = "서울고등학교", score = "통합점수 1,724 LP", isRankUp = false, rank = "7")
    }




}