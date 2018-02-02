package com.lin.linreader.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by ywl on 2016/1/30.
 */
object NetUtil {

    val NETWORN_NONE = 0
    val NETWORN_WIFI = 1
    val NETWORN_MOBILE = 2

    /**
     * 获取网络状态
     *
     * @param context
     * @return
     */
    fun getNetworkState(context: Context): Int {
        val connManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Wifi
        var state: NetworkInfo.State = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .state
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORN_WIFI
        }

        // 3G
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .state
        return if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            NETWORN_MOBILE
        } else NETWORN_NONE
    }

    /**
     * 获取当前网络状态
     *
     * @param context
     * 上下文
     * @return 网络连接返回true；未连接返回false
     */
    fun getNetworkIsConnected(context: Context): Boolean {
        // 获取网络连接管理器
        val manager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return false
        // 如果管理器为null，返回false
        // 获取正在活动的网络信息
        val info = manager.activeNetworkInfo ?: return false
        // 如果网络信息为null，返回false
        // 返回网络是否连接
        return info.isConnected
    }

    /**
     * 网络类型
     *
     * @param context
     * @return true ：是，false ：否
     */
    fun isWiFi(context: Context): Boolean {
        val connectMgr = context

                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectMgr.activeNetworkInfo
        return if (info == null) {
            false
        } else {
            if (info.type == ConnectivityManager.TYPE_WIFI) {
                true
            } else {
                false
            }
        }
    }
}
