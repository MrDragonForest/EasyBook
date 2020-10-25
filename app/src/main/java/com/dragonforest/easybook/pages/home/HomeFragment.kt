package com.dragonforest.easybook.pages.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dragonforest.easybook.R
import com.dragonforest.easybook.beans.blogs.BlogModel
import com.dragonforest.easybook.database.DBManager
import com.dragonforest.easybook.pages.home.dialog.AddBlogDialog
import com.dragonforest.easybook.utils.NavUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *
 * create by DragonForest at 2020/10/25
 */
class HomeFragment(var type: String) : Fragment() {
    private var adapter: BlogListAdapter? = null
    private var addBlogDialog: AddBlogDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BlogListAdapter(null)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter?.onItemClickListener = object : BlogListAdapter.OnItemClickListener {
            override fun onItemClick(blogItem: BlogModel, position: Int) {
                NavUtil.showWebWithBlog(context, blogItem)
            }
        }

        adapter?.blogList = getBlogList()
        adapter?.notifyDataSetChanged()

        fab.setOnClickListener {
            showAddBlogDialog()
        }
    }

    private fun showAddBlogDialog() {
        if (addBlogDialog == null) {
            addBlogDialog = AddBlogDialog(context!!)
            addBlogDialog?.setDefaultValue(
                "",
                "",
                type,
                ""
            )
        }
        addBlogDialog?.show()
    }

    private fun getBlogList(): List<BlogModel>? {
//        var list = ArrayList<BlogModel>()
//        for (i in 0..20) {
//            var blogModel = BlogModel()
//            if (i % 3 == 0) {
//                blogModel.title = "Kotlin+Gradle方便管理依赖"
//                blogModel.shortContent = "真是太爽了"
//                blogModel.lastReadAt = System.currentTimeMillis() - 1000*60*60
//                blogModel.createAt = System.currentTimeMillis()
//                blogModel.readDuration = (1000 * 60 * 60)
//                blogModel.link =
//                    "https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/"
//            }else{
//                blogModel.title = "Apk构建原理深入解析"
//                blogModel.shortContent = "Apk构建由浅入深"
//                blogModel.lastReadAt = System.currentTimeMillis() - 1000*18*60
//                blogModel.createAt = System.currentTimeMillis()
//                blogModel.readDuration = (1000 * 60 * 60)
//                blogModel.link =
//                    "https://mp.weixin.qq.com/s/W-pm9nV_UaSWS2FPJdgbog"
//            }
//            blogModel.type = "技术"
//            if (i % 2 == 0) {
//                var taglist = ArrayList<String>()
//                taglist.add("技术")
//                taglist.add("Android")
//                taglist.add("java")
//                blogModel.tagList = taglist
//            }
//            list.add(blogModel)
//        }
//        return list

        return DBManager.getBlogsByType(type)
    }
}