package com.dragonforest.easybook.utils

import android.content.Context
import android.content.SharedPreferences
import com.dragonforest.easybook.App

/**
 *
 * create by DragonForest at 2020/10/25
 */
object PrefUtils {
    var preference: SharedPreferences? = null

    const val SPNAME = "easy_book_sp"

    init {
        preference = App.globalContext?.getSharedPreferences(SPNAME, Context.MODE_PRIVATE)
    }

    fun getString(key: String): String? {
        return preference?.getString(key, null)
    }

    fun putString(key: String, value: String?) {
        var edit = preference?.edit()
        edit?.putString(key, value)
        edit?.commit()
    }

    fun getBoolean(key: String): Boolean? {
        return preference?.getBoolean(key, false)
    }

    fun putBoolean(key: String, value: Boolean) {
        var edit = preference?.edit()
        edit?.putBoolean(key, value)
        edit?.commit()
    }
}