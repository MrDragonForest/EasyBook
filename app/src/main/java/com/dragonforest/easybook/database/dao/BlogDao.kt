package com.dragonforest.easybook.database.dao

import androidx.room.*
import com.dragonforest.easybook.database.entity.BlogEntity

/**
 *
 * create by DragonForest at 2020/10/25
 */
@Dao
interface BlogDao {

    @Query("SELECT * FROM blog WHERE type = :type ORDER BY createAt DESC")
    fun getBlogsByType(type: String?): List<BlogEntity>?

    @Query("SELECT * FROM blog WHERE tagList like '%'||:tag||'%' ORDER BY createAt DESC")
    fun getBlogsByTag(tag: String?): List<BlogEntity>?

    @Query("SELECT DISTINCT type FROM blog")
    fun getAllTypes(): List<String>?

    @Update
    fun update(blog: BlogEntity)

    @Delete
    fun delete(blog: BlogEntity)

    @Insert
    fun insert(blog: BlogEntity)
}