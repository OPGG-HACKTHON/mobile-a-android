package com.opgg.chai.ui.main.rank.champion

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.ChampionItem
import com.opgg.chai.model.data.CompareCategoryItem
import com.opgg.chai.model.data.RankItem
import com.opgg.chai.model.remote.ApiService
import com.opgg.chai.model.repository.LoLRepository
import com.opgg.chai.util.UserUtils
import kotlinx.coroutines.launch

class RankChampionViewModel @ViewModelInject constructor(
    private val lolRepository: LoLRepository,
    private val apiService: ApiService,
    application: Application
): AndroidViewModel(application) {

    private var _compareCategories = MutableLiveData<List<CompareCategoryItem>>()
    val compareCategories: LiveData<List<CompareCategoryItem>>
        get() = _compareCategories

    private var _championRanks = MutableLiveData<List<RankItem>>()
    val rankItem: LiveData<List<RankItem>>
        get() = _championRanks

    private var _progress = MutableLiveData<Boolean>(false)
    val progress: LiveData<Boolean>
        get() = _progress

    var choseChampion: ChampionItem? = null
    var choseCompareCategory: CompareCategoryItem? = null
        set(value) {
            field = value
            loadRanks()
        }


    fun loadCompareCategories() = viewModelScope.launch {
        val response = lolRepository.getCompareCategory()
        val items = mutableListOf<CompareCategoryItem>()
        response.forEach {
            items.addAll(it.field.map { categoryField ->
                categoryField.parserCompareCategoryItem()
            }.toList())
        }

        items.forEach {
            if(choseCompareCategory != null && it.id == choseCompareCategory!!.id) {
                it.isSelected = true
            }
        }
        _compareCategories.value = items
    }

    fun loadRanks() = viewModelScope.launch {
        if(choseChampion != null && choseCompareCategory != null) {
            _progress.value = true
            val response = apiService.getChampionRank(
                choseChampion!!.id.toString(),
                choseCompareCategory!!.id.toString(),
                UserUtils.userInfo?.school?.id ?: "Dd")

            val myRankResponse = apiService.getChampionRankByUserId(
                choseChampion!!.id.toString(),
                choseCompareCategory!!.id.toString(),
                UserUtils.userInfo?.school?.id ?: "Dd",
                UserUtils.userInfo?.id.toString()
            )

            val items = mutableListOf<RankItem>()
            val notIncludeMeItems = response.filter { it.id != myRankResponse.id }
                .map { it.parserRankItem() }.toList()

            items.add(myRankResponse.parserRankItem(me = true))
            items.addAll(notIncludeMeItems)
            _championRanks.value = items
            _progress.value = false
        }
    }

}