package com.lin.linreader.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lin.linreader.utils.ActivityMgr

/**
 * Created by lin on 2018/2/2.
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun layoutId(): Int
    abstract fun initUi()
    abstract fun initOnClick()
    protected lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        ActivityMgr.instances.addActivity(this)
        context = this
        initUi()
        initOnClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityMgr.instances.removeActivity(this)
    }
}