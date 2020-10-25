package com.dragonforest.easybook.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 *
 * create by DragonForest at 2020/5/6
 */
object ClipboardUtil {
    fun copy(context: Context, text: String) {
        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("label1", text)
        cm.setPrimaryClip(clipData)
    }

    fun getClipboardContent(context: Context): String? {
        val cm =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (cm != null) {
            val data = cm.primaryClip
            if (data != null && data.itemCount > 0) {
                val item = data.getItemAt(0)
                if (item != null) {
                    val sequence = item.coerceToText(context)
                    if (sequence != null) {
                        return sequence.toString()
                    }
                }
            }
        }
        return null
    }

}