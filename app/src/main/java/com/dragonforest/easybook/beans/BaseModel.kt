package com.dragonforest.easybook.beans

import com.google.gson.Gson
import java.io.Serializable

/**
 *
 * create by DragonForest at 2020/10/25
 */
open class BaseModel:Serializable {

    override fun toString(): String {
        return Gson().toJson(this)
    }
}