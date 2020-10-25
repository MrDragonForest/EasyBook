package com.dragonforest.easybook.database

import com.dragonforest.easybook.App
import com.dragonforest.easybook.beans.blogs.BlogModel
import com.dragonforest.easybook.database.mapper.BlogMapper

/**
 *
 * create by DragonForest at 2020/10/25
 */
object DBManager {
    var mDatabase: EasyBookDatabase? = null

    init {
        var context = App.globalContext
        context?.let { mDatabase = EasyBookDatabase.getInstance(context) }
    }

    fun saveBlog(blogModel: BlogModel) {
        var entity = BlogMapper().mapToEntity(blogModel)
        mDatabase?.blogDao()?.insert(entity)
    }

    fun deleteBlog(blogModel: BlogModel) {
        var entity = BlogMapper().mapToEntity(blogModel)
        mDatabase?.blogDao()?.delete(entity)
    }

    fun updateBlog(blogModel: BlogModel) {
        var entity = BlogMapper().mapToEntity(blogModel)
        mDatabase?.blogDao()?.update(entity)
    }

    fun getBlogsByType(type: String): List<BlogModel>? {
        return mDatabase?.blogDao()?.getBlogsByType(type)?.map {
            BlogMapper().mapToData(it)
        }
    }

    fun getBlogsByTag(tag: String): List<BlogModel>? {
        return mDatabase?.blogDao()?.getBlogsByTag(tag)?.map {
            BlogMapper().mapToData(it)
        }
    }

    fun getAllBlogTypes(): List<String>? {
        return mDatabase?.blogDao()?.getAllTypes()
    }

}