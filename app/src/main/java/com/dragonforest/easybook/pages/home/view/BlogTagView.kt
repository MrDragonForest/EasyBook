package com.dragonforest.easybook.pages.home.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.dragonforest.easybook.R
import com.dragonforest.example.recyclerviewtest.utils.DimenUtil

/**
 *
 * create by DragonForest at 2020/10/25
 */
class BlogTagView : LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {
        orientation = HORIZONTAL
    }

    private var tagList: MutableList<String>? = null
    private var MAX_SIZE = 3

    fun setTags(tags: List<String>?) {
        if (tags == null) return
        this.tagList = tags.toMutableList()
        removeAllViews()
        for (i in tags.indices) {
            if (i > MAX_SIZE) return
            var tv = TextView(context)
            setTextStyle(tv, tags[i])
            addView(tv)
        }
    }

    fun addTag(tag: String): Boolean {
        if (tagList?.contains(tag) == true) {
            return false
        }
        var tv = TextView(context)
        setTextStyle(tv, tag)
        addView(tv)
        return true
    }

    private fun setTextStyle(tv: TextView, s: String) {
        tv.text = s
        tv.setBackgroundResource(R.drawable.shape_text_round_bg)
        tv.textSize = 10f
        tv.setPadding(15, 5, 15, 5)
        var lp = LinearLayout.LayoutParams(-2, -2)
        lp.leftMargin = 10
        lp.rightMargin = 10
        tv.layoutParams = lp
    }


}