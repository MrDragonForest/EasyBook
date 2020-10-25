package com.dragonforest.easybook.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.dragonforest.easybook.R
import com.dragonforest.example.recyclerviewtest.utils.DimenUtil


/**
 *
 * create by DragonForest at 2020/5/5
 */
object ToastUtil {
    var toast: Toast? = null
    fun show(
        context: Context,
        msg: String,
        gravity: Int = Gravity.CENTER_VERTICAL,
        xOffset: Int = 0,
        yOffset: Int = DimenUtil.screenHeight() / 4
    ) {
        if (toast == null) {
            toast = Toast(context)
            toast?.view = createView(context)
            toast?.setDuration(Toast.LENGTH_SHORT)
        }
        (toast?.view as TextView).setText(msg)
        toast?.setGravity(gravity, xOffset, yOffset)
        toast?.show()
    }

    private fun createView(context: Context): View? {
        var tv = TextView(context)
        tv.setTextSize(15f)
        tv.setPadding(
            DimenUtil.dp2px(8f).toInt(),
            DimenUtil.dp2px(3f).toInt(),
            DimenUtil.dp2px(8f).toInt(),
            DimenUtil.dp2px(3f).toInt()
        )
        tv.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        tv.setBackgroundResource(R.drawable.shape_text_round_bg)
        tv.setTextColor(Color.parseColor("#000000"))
        return tv
    }
}