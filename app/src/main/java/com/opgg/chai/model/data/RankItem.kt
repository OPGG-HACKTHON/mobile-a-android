package com.opgg.chai.model.data

import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.opgg.chai.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RankItem(val id: Int = 0,
                    val image: String = "",
                    val name: String = "",
                    val score: String = "",
                    val isRankUp: String = "NEW",
                    val summonerLevel: String = "0",
                    val rank: String = "",
                    val me: Boolean = false,
                    val title: String = "",
val viewType: String = "BODY"): Parcelable {

    fun getRankArrowImage(): Int {
        return when(isRankUp) {
            "NEW","SAME" -> R.drawable.ic_arrow_normal
            "UP" -> R.drawable.ic_arrow_up
            "DOWN" -> R.drawable.ic_arrow_down
            else -> R.drawable.ic_arrow_normal
        }
    }

    fun getMeBackground(): Int {
        return if(me) R.drawable.bg_rank_profile_me else R.drawable.bg_rank_profile
    }
}