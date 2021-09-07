package com.opgg.chai.ui.main.battle.invitation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opgg.chai.model.data.battle.BattleUser
import com.opgg.chai.model.repository.BattleRepository
import javax.inject.Inject

class InvitationViewModel @Inject constructor(val repository: BattleRepository): ViewModel() {
    val battleUserInfo = MutableLiveData<BattleUser>()

    init {
        repository.battleUser?.let { battleUserInfo.value = it }
    }
}
