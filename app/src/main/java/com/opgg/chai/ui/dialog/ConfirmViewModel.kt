package com.opgg.chai.ui.dialog

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ConfirmViewModel @Inject constructor(): ViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _contents = MutableLiveData<String>()
    val contents: LiveData<String> = _contents

    // 화면 상에 표시할 정보
    fun setDialogContents(title: String, contents: String) {
        _title.value = title
        _contents.value = contents
    }
}