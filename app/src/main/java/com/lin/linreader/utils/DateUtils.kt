package com.lin.linreader.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {


    /**
     * 获取时间
     *
     * @return
     */
    // 获取当前时间
    val time: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }

    /**
     * 获取时间
     *
     * @return
     */
    // 获取当前时间
    val timeyyyyMMdd: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val formatter = SimpleDateFormat("yyyyMMdd")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }

    /**
     * 获取时间
     *
     * @return
     */
    // 获取当前时间
    val time_yyyy_MM: String
        @SuppressLint("SimpleDateFormat")
        get() {
            val formatter = SimpleDateFormat("yyyy-MM")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }


}
