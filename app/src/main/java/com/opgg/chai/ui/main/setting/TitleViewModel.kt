package com.opgg.chai.ui.main.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.auth.User
import com.opgg.chai.model.data.title.TitleHistoryItem
import com.opgg.chai.model.data.title.TitleItem
import com.opgg.chai.model.repository.SettingRepository
import com.opgg.chai.util.UserUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TitleViewModel @Inject constructor(val settingRepository: SettingRepository) : ViewModel() {
    val userInfo: User? = UserUtils.userInfo

    private val _titleHistory = MutableLiveData<List<TitleHistoryItem>>()
    val titleHistory: LiveData<List<TitleHistoryItem>> = _titleHistory

    private val _titleList = MutableLiveData<List<TitleItem>>()
    val titleList: LiveData<List<TitleItem>> = _titleList

    init {
        viewModelScope.launch {
            getTitleHistory()
            getTitleList()
        }
    }

    suspend fun getTitleHistory() {
        withContext(Dispatchers.IO) {
            val id = userInfo?.id ?: 1
            val result = settingRepository.getUserTitleHistory(id)

            withContext(Dispatchers.Main) { _titleHistory.value = result }
        }
    }

    suspend fun getTitleList() {
        withContext(Dispatchers.IO) {
            val id = userInfo?.id ?: 1
            val result = settingRepository.getUserTitle(id)

            withContext(Dispatchers.Main) { _titleList.value = result }
        }
    }
}