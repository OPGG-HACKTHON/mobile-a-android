package com.opgg.chai.ui.main.battle

import android.content.Intent
import androidx.lifecycle.ViewModel

class BattleSearchViewModel: ViewModel() {

    // 배틀을 찾기 위한 함수
    fun searchChaiMember(name: String) {}

    //차이 앱 공유를 위한 인텐트 생성
    fun shareChai(): Intent {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "chai app")
        }

        return Intent.createChooser(intent, "Share Chai")
    }
}
