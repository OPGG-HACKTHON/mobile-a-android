package com.opgg.chai.ui.main.rank.champion

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opgg.chai.model.data.ChampionItem

class RankChampionChoiceViewModel @ViewModelInject constructor(
    application: Application): AndroidViewModel(application) {

    private var _champions = MutableLiveData<List<ChampionItem>>()
    val champions: LiveData<List<ChampionItem>>
        get() = _champions

    fun loadChampions() {
        _champions.value = (0 until 50).map {
            ChampionItem(id = it, name = "name = $it", image = "")
        }.toList()
    }


}