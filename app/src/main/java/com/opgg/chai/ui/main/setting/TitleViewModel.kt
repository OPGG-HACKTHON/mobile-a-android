package com.opgg.chai.ui.main.setting

import androidx.lifecycle.ViewModel
import com.opgg.chai.model.data.auth.User
import com.opgg.chai.util.UserUtils
import javax.inject.Inject

class TitleViewModel constructor(): ViewModel() {
    val userInfo: User? = UserUtils.userInfo


}