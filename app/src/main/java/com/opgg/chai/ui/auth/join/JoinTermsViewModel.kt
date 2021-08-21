package com.opgg.chai.ui.auth.join

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.opgg.chai.R

class JoinTermsViewModel(val adapter: JoinTermsAdapter) : ViewModel() {
    val _isSelectAll: MutableLiveData<Boolean> = adapter.isSelectAll
    lateinit var navController: NavController

    fun selectAll() {
        adapter.selectCheckAll()
    }

    fun agreeAllTerms() {
        if (!_isSelectAll.value!!) return

        navController.navigate(R.id.action_joinFragment_to_joinFormFragment)
    }

    fun backFragment() = navController.navigateUp()
}