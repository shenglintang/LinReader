package com.lin.linreader.utils

import android.util.Log

class LogUtil private constructor() {
    init {
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {
        private val FILTER = "lin"

        var isDebug = true// 是否打印在gradle里配置


        // 下面是传入自定义tag的函数
        fun i(tag: String, msg: String) {
            if (isDebug)
                Log.i(FILTER, tag + " :" + msg)
        }

        fun d(tag: String, msg: String) {
            if (isDebug)
                Log.d(FILTER, tag + " :" + msg)
        }

        fun e(msg: String) {
            if (isDebug)
                Log.e(FILTER, msg)
        }

        fun v(tag: String, msg: String) {
            if (isDebug)
                Log.v(FILTER, tag + " :" + msg)
        }
    }
}
