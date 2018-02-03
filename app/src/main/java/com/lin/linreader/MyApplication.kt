package com.lin.linreader

import android.app.Application
import android.content.Context

/**
 * Created by Administrator on 2016/12/16.
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: Context? = null
            private set
    }
}
