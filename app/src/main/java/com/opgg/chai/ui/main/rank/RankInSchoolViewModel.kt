package com.opgg.chai.ui.main.rank

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.opgg.chai.model.data.RankItem

class RankInSchoolViewModel @ViewModelInject constructor(application: Application): AndroidViewModel(application) {

    private var _ranks = MutableLiveData<List<RankItem>>()
    val ranks: MutableLiveData<List<RankItem>>
        get() = _ranks


    private var _school = MutableLiveData<RankItem>()
    val school: MutableLiveData<RankItem>
        get() = _school



    fun loadRank() {
        val list = mutableListOf<RankItem>()
        for(i in 0 until 10) {
            list.add(RankItem(id = i.toLong(), name = "Name = $i", score = "Score = $i", isRankUp = i % 2 == 1, rank = "$i"))
        }
        _ranks.value = list
    }

    fun loadSchool() {
        val school = RankItem(id = 1, name = "서울고등학교", score = "통합점수 1,724 LP", isRankUp = false, rank = "7")
        _school.value = school
    }
}