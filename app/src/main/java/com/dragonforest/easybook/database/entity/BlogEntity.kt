package com.dragonforest.easybook.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * create by DragonForest at 2020/10/25
 */
@Entity(tableName = "blog")
data class BlogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "shortContent") var shortContent: String?,
    @ColumnInfo(name = "createAt") var createAt: Long?,
    @ColumnInfo(name = "lastReadAt") var lastReadAt: Long?,
    @ColumnInfo(name = "readDuration") var readDuration: Long?,
    @ColumnInfo(name = "readCounts") var readCounts: Int?,
    @ColumnInfo(name = "link") var link: String?,
    @ColumnInfo(name = "type") var type: String?,
    @ColumnInfo(name = "tagList") var tagList: String?
)