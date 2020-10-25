package com.dragonforest.easybook.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.dragonforest.easybook.R
import com.dragonforest.easybook.beans.blogs.BlogModel
import com.dragonforest.easybook.cosntrants.IntentDefine
import com.dragonforest.easybook.database.DBManager
import com.dragonforest.easybook.pages.home.dialog.AddTagDialog
import com.dragonforest.easybook.utils.ClipboardUtil
import com.dragonforest.easybook.utils.ToastUtil
import com.google.gson.Gson
import com.shehuan.nicedialog.BaseNiceDialog
import com.shehuan.nicedialog.NiceDialog
import com.shehuan.nicedialog.ViewConvertListener
import com.shehuan.nicedialog.ViewHolder
import kotlinx.android.synthetic.main.activity_web_detail.*

/**
 *
 * create by DragonForest at 2020/10/25
 */
class WebDetailActivity : AppCompatActivity() {
    var currentWebUrl: String? = ""
    var blogData: BlogModel? = null
    var readStartAt: Long = 0L
    var addTagDialog: AddTagDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_detail)
        initParams()
        initView()

        readStartAt = System.currentTimeMillis()
        blogData?.readCounts?.plus(1)
    }

    private fun initParams() {
        currentWebUrl = intent.getStringExtra(IntentDefine.INTENT_KEY_WEB_URL)
        var blogStr = intent.getStringExtra(IntentDefine.INTENT_KEY_WEB_BLOG_DATA)
        if (!TextUtils.isEmpty(blogStr)) {
            blogData = Gson().fromJson(blogStr, BlogModel::class.java)
            blogData?.link?.let { currentWebUrl = it }
        }
    }

    @SuppressLint("RestrictedApi")
    private fun initView() {
        progressbar.visibility = View.GONE
        tv_title.text = "加载中"
        blogData?.let { fab_add_tag.visibility = View.VISIBLE }
        fab_add_tag.setOnClickListener {
            showAddTagDialog()
        }
        iv_back.setOnClickListener {
            finish()
        }

        iv_menu.setOnClickListener {
            showBottomMenu()
        }

        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE)
        swipeRefreshLayout.setOnRefreshListener { loadPage() }

        webview.visibility = View.GONE
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String
            ): Boolean {
                currentWebUrl = url
                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.visibility = View.VISIBLE
                view?.scrollTo(0, 0)
                swipeRefreshLayout.isRefreshing = false
            }
        }
        webview.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                tv_title.text = title
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (progressbar.visibility == View.GONE) {
                    progressbar.visibility = View.VISIBLE
                }
                progressbar.progress = newProgress
                if (newProgress == 100) {
                    progressbar.visibility = View.GONE
                }
            }
        }
        loadPage()
    }

    private fun showAddTagDialog() {
        if (blogData == null) return
        if (addTagDialog == null) {
            addTagDialog = AddTagDialog(this)
            addTagDialog?.setDefaultValue(blogData!!)
        }
        addTagDialog?.show()
        addTagDialog?.onSubmitListener = object : AddTagDialog.OnSubmitListener {
            override fun onSubmit(tag: String) {
                blogData?.addTag(tag)
                blogData?.let { DBManager.updateBlog(it) }
                ToastUtil.show(this@WebDetailActivity,"保存成功")
            }
        }
    }

    private fun loadPage() {
        webview.loadUrl(currentWebUrl)
    }

    private fun showBottomMenu() {
        NiceDialog.init()
            .setLayoutId(R.layout.view_web_menu)
            .setConvertListener(object : ViewConvertListener() {
                public override fun convertView(holder: ViewHolder, dialog: BaseNiceDialog) {
                    holder.setOnClickListener(R.id.ll_copy) {
                        // 复制
                        ClipboardUtil.copy(this@WebDetailActivity, currentWebUrl ?: "")
                        ToastUtil.show(this@WebDetailActivity, "已复制到剪切板")
                        dialog.dismiss()
                    }
                    holder.setOnClickListener(R.id.ll_share) {
                        // 分享
                        val intent = ShareCompat.IntentBuilder
                            .from(this@WebDetailActivity)
                            .setText(currentWebUrl ?: "")
                            .setChooserTitle("选择应用")
                            .setType("text/plain")
                            .intent
                        startActivity(Intent.createChooser(intent, "选择分享应用"))
                        dialog.dismiss()
                    }
                }
            })
            .setOutCancel(true)
            .setDimAmount(0.3f)
            .setGravity(Gravity.BOTTOM)
            .show(supportFragmentManager)
    }


    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        blogData?.lastReadAt = System.currentTimeMillis()
        blogData?.readDuration?.plus(System.currentTimeMillis() - readStartAt)
        blogData?.let { DBManager.updateBlog(it) }
    }
}