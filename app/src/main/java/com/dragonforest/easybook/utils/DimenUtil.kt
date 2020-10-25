package com.dragonforest.example.recyclerviewtest.utils

import android.content.res.Resources

/**
 *
 * create by DragonForest at 2020/5/1
 */
object DimenUtil {
    fun dp2px(dp: Float): Float {
        return Resources.getSystem().displayMetrics.density * dp
    }

    fun px2dp(px: Float):Float {
        return px / Resources.getSystem().displayMetrics.density
    }

    fun screenHeight():Int{
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun screenWidth():Int{
        return Resources.getSystem().displayMetrics.widthPixels
    }

}