package com.opgg.chai.ui.main.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.auth.Title
import com.opgg.chai.model.data.auth.User
import com.opgg.chai.model.data.title.TitleHistoryItem
import com.opgg.chai.model.repository.SettingRepository
import com.opgg.chai.util.UserUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class TitleViewModel @Inject constructor(val settingRepository: SettingRepository) : ViewModel() {
    val userInfo: User? = UserUtils.userInfo

    private val _titleHistory = MutableLiveData<List<TitleHistoryItem>>()
    val titleHistory: LiveData<List<TitleHistoryItem>> = _titleHistory

    private val _titleList = MutableLiveData<List<Title>>()
    val titleList: LiveData<List<Title>> = _titleList

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

    fun setUserTitle(title: Title?) {
        title?.let {
            val titleId = it.id ?: 1
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val userId = userInfo?.id ?: 1
                        val body = HashMap<String, Int>().apply {
                            put("id", titleId)
                        }
                        settingRepository.setUserTitle(userId, body)

                        UserUtils.userInfo?.title = title
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}