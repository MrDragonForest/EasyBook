package com.dragonforest.easybook.database.mapper

import com.dragonforest.easybook.beans.blogs.BlogModel
import com.dragonforest.easybook.database.entity.BlogEntity
import java.lang.StringBuilder

/**
 *
 * create by DragonForest at 2020/10/25
 */
class BlogMapper : BaseMapper<BlogModel, BlogEntity>() {
    override fun mapToEntity(data: BlogModel): BlogEntity {
        return BlogEntity(
            data.id,
            data.title,
            data.shortContent,
            data.createAt,
            data.lastReadAt,
            data.readDuration,
            data.readCounts,
            data.link,
            data.type,
            tagList2String(data.tagList)
        )
    }

    override fun mapToData(entity: BlogEntity): BlogModel {
        var data = BlogModel()
        data.id = entity.id
        data.title = entity.title
        data.shortContent = entity.shortContent
        data.createAt = entity.createAt
        data.lastReadAt = entity.lastReadAt
        data.readDuration = entity.readDuration
        data.readCounts = entity.readCounts
        data.link = entity.link
        data.type = entity.type
        data.tagList = string2TagList(entity.tagList)
        return data
    }

    private fun tagList2String(tagList: List<String>?): String? {
        if (tagList == null) return null
        var sb = StringBuilder()
        tagList.forEach {
            sb.append(it).append(",")
        }
        return sb.substring(0, sb.length - 1)
    }

    private fun string2TagList(str: String?): MutableList<String>? {
        if (str == null) return null
        return str.split(",").toMutableList()
    }
}