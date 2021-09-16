package com.opgg.chai.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object SharedPreferenceUtil {
    const val accessTokenKey = "access_token"
    const val emailKey = "email"
    private lateinit var sharedPreference: SharedPreferences

    fun initSharedPreference(context: Context) {
        if(!this::sharedPreference.isInitialized)
            sharedPreference = context.getSharedPreferences("${context.packageName}.shared", Context.MODE_PRIVATE)
    }

    fun addValue(key: String, value: String) {
        Log.d("token", "$key : $value")
        sharedPreference.edit().putString(key, value).commit()
    }

    fun getValue(key: String, defaultValue: String = ""): String? {
        return sharedPreference.getString(key, defaultValue)
    }

    fun removeValue(key: String) {
        sharedPreference.edit().remove(key).commit()
    }

}