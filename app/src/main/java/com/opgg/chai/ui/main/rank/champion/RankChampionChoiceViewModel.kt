package com.opgg.chai.ui.main.rank.champion

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.model.data.CompareCategoryItem
import com.opgg.chai.model.repository.LoLRepository
import com.opgg.chai.util.Event
import com.opgg.chai.util.emit
import kotlinx.coroutines.launch

class RankChampionChoiceViewModel @ViewModelInject constructor(
    application: Application,
    private val lolRepository: LoLRepository
    ): AndroidViewModel(application) {

    private var _champions = MutableLiveData<List<ChampionItem>>()
    val champions: LiveData<List<ChampionItem>>
        get() = _champions

    private var _compareCategories = MutableLiveData<List<CompareCategoryItem>>()
    val compareCategories: LiveData<List<CompareCategoryItem>>
        get() = _compareCategories

    private var _showResultFragmentEvent = MutableLiveData<Event<Unit>>()
    val showResultFragmentEvent: LiveData<Event<Unit>>
        get() = _showResultFragmentEvent


    var choseChampion: ChampionItem? = null
    var choseCompareCategory: CompareCategoryItem? = null

    fun loadChampions() = viewModelScope.launch {
        val response = lolRepository.getChampions()

        val items = response.map {
            it.parserChampionItem()
        }.toList()
        _champions.value = items
    }

    fun loadCompareCategories() = viewModelScope.launch {
        val response = lolRepository.getCompareCategory()

        val items = mutableListOf<CompareCategoryItem>()
        response.forEach {
            items.addAll(it.field.map { categoryField ->
                categoryField.parserCompareCategoryItem()
            }.toList())
        }
        _compareCategories.value = items
    }

    fun showChampionResultEvent() {
        if(choseChampion != null && choseCompareCategory != null) {
            _showResultFragmentEvent.emit()

        }
    }
}