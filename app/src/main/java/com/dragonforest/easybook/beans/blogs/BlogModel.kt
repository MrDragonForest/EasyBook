package com.dragonforest.easybook.beans.blogs

import com.dragonforest.easybook.beans.BaseModel

/**
 *
 * create by DragonForest at 2020/10/25
 */
class BlogModel : BaseModel() {
    var id: Int = 0
    var title: String? = ""
    var shortContent: String? = ""
    var createAt: Long? = 0L
    var lastReadAt: Long? = 0L
    var readDuration: Long? = 0L
    var readCounts: Int? = 0
    var link: String? = null
    var type: String? = null
    var tagList: MutableList<String>? = null

    fun addTag(tag: String) {
        if (tagList == null) {
            tagList = ArrayList<String>()
        }
        tagList?.add(0, tag)
    }
}