package com.dragonforest.easybook.pages.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dragonforest.easybook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var homePageAdapter: HomePageAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        btn_splash.setOnClickListener {
//            NavUtil.showSplash(
//                this,
//                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603611076229&di=dbc1cee744c920adba9daa996abddf90&imgtype=0&src=http%3A%2F%2Fa1.att.hudong.com%2F05%2F00%2F01300000194285122188000535877.jpg",
//                "https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/",
//                true
//            )
//        }
    }

    private fun initView() {
        homePageAdapter = HomePageAdapter(supportFragmentManager)
        viewPager.adapter = homePageAdapter
        homePageAdapter?.addItem("首页",HomeFragment("first"))
        homePageAdapter?.addItem("工具",HomeFragment("tools"))
        homePageAdapter?.addItem("其他",HomeFragment("other"))
        tabLayout.setUpWithViewPager(viewPager)

    }
}