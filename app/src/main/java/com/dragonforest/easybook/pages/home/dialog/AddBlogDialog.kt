package com.dragonforest.easybook.pages.home.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import com.dragonforest.easybook.R
import com.dragonforest.easybook.beans.blogs.BlogModel
import com.dragonforest.easybook.database.DBManager
import com.dragonforest.easybook.utils.ClipboardUtil
import com.dragonforest.easybook.utils.ToastUtil
import kotlinx.android.synthetic.main.dialog_add_blog.*

/**
 *
 * create by DragonForest at 2020/10/25
 */
class AddBlogDialog : AlertDialog {
    constructor(context: Context) : super(context)

    private var defaultTitle: String? = ""
    private var defaultShortContent: String? = ""
    private var defaultType: String? = ""
    private var defaultLink: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_blog)
        initView()
        initShow()
    }

    fun setDefaultValue(title: String?, shortContent: String?, type: String?, link: String?) {
        this.defaultTitle = title
        this.defaultShortContent = shortContent
        this.defaultType = type
        this.defaultLink = link
    }

    private fun initShow() {

        ed_title.setText(defaultTitle)
        ed_short_content.setText(defaultShortContent)
        ed_type.setText(defaultType)
        ed_link.setText(defaultLink)

        var clipUrl = ClipboardUtil.getClipboardContent(context) ?: return
        if (clipUrl.startsWith("http://") || clipUrl.startsWith("https://")) {
            ed_link.setText(clipUrl)
            tv_tip_link.visibility = View.VISIBLE
        }
    }

    private fun initView() {
        setCancelable(false)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        btn_submit.setOnClickListener {
            doSubmit()
        }
        iv_close.setOnClickListener { dismiss() }
    }

    private fun doSubmit() {
        var title = ed_title.text.toString()
        var shortContent = ed_short_content.text.toString()
        var type = ed_type.text.toString()
        var link = ed_link.text.toString()
        if (TextUtils.isEmpty(title)) {
            ToastUtil.show(context, "标题不可为空")
            return
        }
        if (TextUtils.isEmpty(shortContent)) {
            ToastUtil.show(context, "简介不能为空")
            return
        }
        if (TextUtils.isEmpty(type)) {
            ToastUtil.show(context, "类型不能为空")
            return
        }
        if (TextUtils.isEmpty(link)) {
            ToastUtil.show(context, "地址不能为空")
            return
        }
        var blogModel = BlogModel()
        blogModel.title = title
        blogModel.shortContent = shortContent
        blogModel.type = type
        blogModel.link = link
        blogModel.createAt = System.currentTimeMillis()
        DBManager.saveBlog(blogModel)
        ToastUtil.show(context, "保存成功")
        dismiss()
    }
}