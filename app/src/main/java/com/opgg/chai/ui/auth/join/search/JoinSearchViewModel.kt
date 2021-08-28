package com.opgg.chai.ui.auth.join.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.opgg.chai.model.data.auth.SchoolInfo
import com.opgg.chai.model.remote.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JoinSearchViewModel(val authService: AuthService) : ViewModel() {
    lateinit var navController: NavController
    var division: String = "고등학교"

    private val _schoolList: MutableLiveData<List<SchoolInfo>> = MutableLiveData()
    val schoolList: LiveData<List<SchoolInfo>> = _schoolList

    //학교 검색
    fun searchSchool(word: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val list: List<SchoolInfo>? = authService.searchSchool(division, word)
                    list?.let { setSchoolData(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    suspend fun setSchoolData(list: List<SchoolInfo>) {
        withContext(Dispatchers.Main) {
            _schoolList.value = list
        }
    }

    fun movePageBack() {
        navController.popBackStack()
    }
}