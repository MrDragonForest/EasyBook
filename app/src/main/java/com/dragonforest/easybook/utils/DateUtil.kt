package com.dragonforest.easybook.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * create by DragonForest at 2020/10/25
 */
object DateUtil {
    private val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")

    fun calculateDateFromNowDistance(mills: Long?): String? {
        if (mills == null || mills == 0L) return ""
        return calculateDateFromNowDistance(Date(mills))
    }

    /**
     * 计算距离当前时间的距离的字符串
     * 例如： 传入 2019/12/30 今天是 2020/1/2  计算结果就是 2天前
     *
     * @param date
     */
    fun calculateDateFromNowDistance(date: Date?): String? {
        val calendarFrom = Calendar.getInstance()
        calendarFrom.time = date
        val fromDay = calendarFrom[Calendar.DAY_OF_YEAR]
        val fromHour = calendarFrom[Calendar.HOUR_OF_DAY]
        val fromMinute = calendarFrom[Calendar.MINUTE]
        val calendarTo = Calendar.getInstance()
        calendarTo.time = Date()
        val toDay = calendarTo[Calendar.DAY_OF_YEAR]
        val toHour = calendarTo[Calendar.HOUR_OF_DAY]
        val toMinute = calendarTo[Calendar.MINUTE]
        val dayDiff = toDay - fromDay
        val hourDiff = toHour - fromHour
        val minuteDiff = toMinute - fromMinute
        if (dayDiff > 30) {
            return formatDate(date)
        }
        if (dayDiff > 0) {
            return dayDiff.toString() + "天前"
        }
        if (hourDiff > 0) {
            return hourDiff.toString() + "小时前"
        }
        return if (minuteDiff > 0) {
            minuteDiff.toString() + "分钟前"
        } else "刚刚"
    }

    fun millis2Minutes(milli: Long?): Int {
        if (milli == null) return 0
        return (milli / 1000 / 60).toInt()
    }

    fun formatDate(date: Date?, format: String? = null): String? {
        if (date == null) return ""
        if (format == null) {
            return simpleDateFormat.format(format)
        }
        val sdf1 = SimpleDateFormat(format)
        return sdf1.format(date)
    }
}