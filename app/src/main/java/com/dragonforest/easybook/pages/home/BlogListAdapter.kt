package com.dragonforest.easybook.pages.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dragonforest.easybook.R
import com.dragonforest.easybook.beans.blogs.BlogModel
import com.dragonforest.easybook.utils.DateUtil
import com.dragonforest.easybook.utils.NavUtil
import kotlinx.android.synthetic.main.item_blog.view.*

/**
 *
 * create by DragonForest at 2020/10/25
 */
class BlogListAdapter(var blogList: List<BlogModel>?) :
    RecyclerView.Adapter<BlogListAdapter.BlogViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun getItemCount(): Int {
        return blogList?.size ?: 0
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        var blogItem = blogList?.get(position) ?: return
        initItem(holder, blogItem, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
        return BlogViewHolder(v)
    }

    private fun initItem(
        holder: BlogViewHolder,
        blogItem: BlogModel,
        position: Int
    ) {
        holder.itemView.tv_title.text = blogItem.title
        holder.itemView.tv_short_content.text = blogItem.shortContent
        holder.itemView.tv_last_read_at.text =
            DateUtil.calculateDateFromNowDistance(blogItem.lastReadAt)
        holder.itemView.tv_read_duration.text =
            "${DateUtil.millis2Minutes(blogItem.readDuration)}分钟"
        holder.itemView.blogTagView.setTags(blogItem.tagList)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(blogItem,position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(blogItem: BlogModel, position: Int)
    }
}