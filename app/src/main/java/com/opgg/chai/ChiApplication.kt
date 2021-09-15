package com.opgg.chai

import android.app.Application
import com.opgg.chai.util.SharedPreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChiApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPreferenceUtil.initSharedPreference(this)
    }
}