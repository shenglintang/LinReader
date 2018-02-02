package com.lin.linreader.utils

import android.support.v7.app.AppCompatActivity

/**
 * Created by lin on 2018/2/2.
 * 活动管理器
 */
class ActivityMgr private constructor() {
    private val activityList = mutableListOf<AppCompatActivity>()

    companion object {
        val instances by lazy {
            ActivityMgr()
        }
    }

    fun addActivity(activity: AppCompatActivity) {
        activityList.add(0, activity)
    }

    fun removeActivity(activity: AppCompatActivity) {
        activityList.remove(activity)
    }

    fun finishAll() {
        for (activity in activityList) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }
}