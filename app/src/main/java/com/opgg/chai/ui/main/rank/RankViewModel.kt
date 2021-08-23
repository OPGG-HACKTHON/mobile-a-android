package com.opgg.chai.ui.main.rank

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.opgg.chai.model.data.RankItem

class RankViewModel @ViewModelInject constructor(application: Application): AndroidViewModel(application) {

    private var _rank = MutableLiveData<ArrayList<RankItem>>()
    val rank: MutableLiveData<ArrayList<RankItem>>
        get() = _rank


    fun loadRank() {
        val list = arrayListOf<RankItem>()
        for(i in 0 until 10) {
            list.add(RankItem(id = i.toLong(), name = "Name = $i", score = "Score = $i", isRankUp = i % 2 == 1, rank = "$i"))
        }
        _rank.value = list
    }
}