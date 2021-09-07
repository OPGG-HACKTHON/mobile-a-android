package com.opgg.chai.ui.main.rank.champion

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opgg.chai.model.data.CompareCategoryItem
import com.opgg.chai.model.data.RankItem

class RankChampionViewModel @ViewModelInject constructor(
    application: Application
): AndroidViewModel(application) {

    private var _compareCategories = MutableLiveData<List<CompareCategoryItem>>()
    val compareCategories: LiveData<List<CompareCategoryItem>>
        get() = _compareCategories

    private var _championRanks = MutableLiveData<List<RankItem>>()
    val rankItem: LiveData<List<RankItem>>
        get() = _championRanks



    fun loadCompareCategories() {
        _compareCategories.value = (0 until 30).map {
            CompareCategoryItem(id = it, name = "name = ${it * 10000}")
        }.toList()
    }

    fun loadRanks() {
        _championRanks.value = (0 until 10).map {
            RankItem(id = it.toLong(), name = "name = ${it}", score = "score = $it", isRankUp = it % 2 == 1, rank = "$it")
        }.toList()
    }

}