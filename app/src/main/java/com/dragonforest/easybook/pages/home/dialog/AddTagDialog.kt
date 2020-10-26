package com.dragonforest.easybook.pages.home.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import com.dragonforest.easybook.R
import com.dragonforest.easybook.beans.blogs.BlogModel
import com.dragonforest.easybook.utils.ToastUtil
import kotlinx.android.synthetic.main.dialog_add_tag.*

/**
 *
 * create by DragonForest at 2020/10/25
 */
class AddTagDialog : AlertDialog {
    constructor(context: Context) : super(context)

    var blogModel: BlogModel? = null
    var onSubmitListener: OnSubmitListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_tag)
        initView()
        initShow()
    }

    fun setDefaultValue(blogItem: BlogModel) {
        this.blogModel = blogItem
    }

    private fun initShow() {
        blogTagView.setTags(blogModel?.tagList)
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
        var tag = ed_tag.text.toString()
        if (TextUtils.isEmpty(tag)) {
            ToastUtil.show(context, "标签不能为空！")
            return
        } else {
            if (blogModel?.tagList?.contains(tag) == true) {
                ToastUtil.show(context, "标签已经存在！")
                return
            }
            onSubmitListener?.onSubmit(tag)
            dismiss()
        }
    }

    interface OnSubmitListener {
        fun onSubmit(tag: String)
    }
}