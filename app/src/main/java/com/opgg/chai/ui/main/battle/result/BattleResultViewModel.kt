package com.opgg.chai.ui.main.battle.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opgg.chai.model.repository.BattleRepository
import javax.inject.Inject

class BattleResultViewModel @Inject constructor(val repository: BattleRepository): ViewModel() {
    val resultTitle: MutableLiveData<String> = MutableLiveData()
}
