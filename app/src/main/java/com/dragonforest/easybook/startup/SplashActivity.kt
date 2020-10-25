package com.dragonforest.easybook.startup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.dragonforest.easybook.pages.home.MainActivity
import com.dragonforest.easybook.R
import com.dragonforest.easybook.beans.configs.SplashConfig
import com.dragonforest.easybook.cosntrants.IntentDefine
import com.dragonforest.easybook.cosntrants.PrefDefine
import com.dragonforest.easybook.utils.NavUtil
import com.dragonforest.easybook.utils.PrefUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var adviceImageUrl: String? = ""
    private var adviceImageLink: String? = ""
    private var startMode: Int? = 0      // 0:默认  1：打开后回到原来的activity

    //广告倒计时
    private var lastSeconds = 5
    private var delayHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenStyle()
        var isShowAd = isShowAd()
        if (isShowAd) {
            showAd()
        } else {
            goNext()
        }
    }

    private fun goNext() {
        if (startMode == 0) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }

    private fun setFullScreenStyle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }


    private fun isShowAd(): Boolean {
        adviceImageUrl = intent.getStringExtra(IntentDefine.INTENT_KEY_SPLASH_BG_IMAGE)
        adviceImageLink = intent.getStringExtra(IntentDefine.INTENT_KEY_SPLASH_BG_LINK)
        startMode = intent.getIntExtra(IntentDefine.INTENT_KEY_SPLASH_START_MODE, 0)
        var isShowAd = !TextUtils.isEmpty(adviceImageUrl) && !TextUtils.isEmpty(adviceImageLink)
        if (!isShowAd) {
            var splashConfigStr =
                PrefUtils.getString(PrefDefine.PREF_KEY_SPLASH_CONFIG) ?: return isShowAd
            var splashConfig = Gson().fromJson(splashConfigStr, SplashConfig::class.java)
            splashConfig.adviceImageLink?.let { adviceImageLink = it }
            splashConfig.adviceImageUrl?.let { adviceImageUrl = it }
            splashConfig.startMode?.let { startMode = it }
            isShowAd = !TextUtils.isEmpty(adviceImageUrl) && !TextUtils.isEmpty(adviceImageLink)
        }
        return isShowAd
    }

    private fun showAd() {
        setContentView(R.layout.activity_splash)
        Glide.with(this).load(adviceImageUrl).into(iv_bg)
        //开始计时
        var runnable = object : Runnable {
            override fun run() {
                if (lastSeconds > 0) {
                    tv_time.text = "${lastSeconds}s | 跳过"
                    lastSeconds--
                    delayHandler.postDelayed(this, 1000L)
                } else {
                    goNext()
                }
            }
        }
        delayHandler.post(runnable)
        tv_time.setOnClickListener {
            delayHandler.removeCallbacksAndMessages(null)
            goNext()
        }
        iv_bg.setOnClickListener {
            //跳转链接
            NavUtil.showWeb(this,adviceImageLink)
        }

    }
}