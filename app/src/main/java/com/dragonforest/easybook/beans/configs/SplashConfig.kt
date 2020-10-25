package com.dragonforest.easybook.beans.configs

import com.dragonforest.easybook.beans.BaseModel

/**
 *
 * create by DragonForest at 2020/10/25
 */
class SplashConfig: BaseModel() {
    var adviceImageUrl: String? = ""
    var adviceImageLink: String? = ""
    var startMode: Int? = 0      // 0:默认  1：打开后回到原来的activity


}