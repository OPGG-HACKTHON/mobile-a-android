package com.opgg.chai.ui.main.battle.search

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opgg.chai.model.data.battle.BattleUser
import com.opgg.chai.model.repository.BattleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BattleSearchViewModel @Inject constructor(val battleRepository: BattleRepository) : ViewModel() {
    val userName = MutableLiveData<String>("")
    private val _battleUserList = MutableLiveData<List<BattleUser?>>()
    val battleUserList: MutableLiveData<List<BattleUser?>> = _battleUserList

    // 배틀 유저를 찾기 위한 함수
    fun searchChaiMember() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = battleRepository.getBattleUserList(userName.value!!)
                    updateBattleUserList(result)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            userName.value = ""
        }
    }

    //차이 앱 공유를 위한 인텐트 생성
    fun shareChai(): Intent {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "chai app")
        }

        return Intent.createChooser(intent, "Share Chai")
    }

    // 데이터를 받아 배틀 유저 리스트 업데이트
    suspend fun updateBattleUserList(list: List<BattleUser?>) {
        withContext(Dispatchers.Main) {
            _battleUserList.value = list
        }
    }

    fun setBattleUser(userInfo: BattleUser) {
        battleRepository.setUser(userInfo)
    }

}
