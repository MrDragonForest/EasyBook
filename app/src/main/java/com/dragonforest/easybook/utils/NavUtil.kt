package com.dragonforest.easybook.utils

import android.content.Context
import android.content.Intent
import com.dragonforest.easybook.beans.blogs.BlogModel
import com.dragonforest.easybook.cosntrants.IntentDefine
import com.dragonforest.easybook.pages.WebDetailActivity
import com.dragonforest.easybook.startup.SplashActivity

/**
 *
 * create by DragonForest at 2020/10/25
 */
object NavUtil {
    /**
     * 显示splash页面
     */
    fun showSplash(
        context: Context,
        adviceImage: String,
        adviceLink: String,
        isGoBack: Boolean = false
    ) {
        var intent = Intent(context, SplashActivity::class.java)
        intent.putExtra(IntentDefine.INTENT_KEY_SPLASH_BG_IMAGE, adviceImage)
        intent.putExtra(IntentDefine.INTENT_KEY_SPLASH_BG_LINK, adviceLink)
        if (isGoBack) {
            intent.putExtra(IntentDefine.INTENT_KEY_SPLASH_START_MODE, 1)
        }
        context.startActivity(intent)
    }

    /**
     * 显示网页
     */
    fun showWeb(context: Context?, url: String?) {
        if (context == null) return
        var intent = Intent(context, WebDetailActivity::class.java)
        intent.putExtra(IntentDefine.INTENT_KEY_WEB_URL, url)
        context.startActivity(intent)
    }

    fun showWebWithBlog(context: Context?,blogModel: BlogModel) {
        if (context == null) return
        var intent = Intent(context, WebDetailActivity::class.java)
        intent.putExtra(IntentDefine.INTENT_KEY_WEB_BLOG_DATA, blogModel.toString())
        context.startActivity(intent)
    }
}