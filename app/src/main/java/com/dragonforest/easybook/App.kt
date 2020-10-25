package com.dragonforest.easybook

import android.app.Application

/**
 *
 * create by DragonForest at 2020/10/25
 */
class App : Application() {
    companion object {
        var globalContext: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        globalContext = this
    }
}