package com.lin.linreader.http

import android.content.Context
import rx.Subscriber
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Created by lin on 2018/2/2.
 */
class HttpSubscriber<T>(var subscriberOnListener: SubscriberOnListener<T>, var context: Context) : Subscriber<T>() {

    fun onUnsubscribe() {
        if (!this.isUnsubscribed) {
            this.unsubscribe()
        }
    }

    /**
     * 访问网络开始前（可以处理缓存）
     */
    override fun onStart() {
        super.onStart()
    }

    override fun onNext(t: T) {
        subscriberOnListener.onSucceed(t)
    }

    override fun onCompleted() {
        onUnsubscribe()
    }

    override fun onError(e: Throwable) {
        when (e) {
            is SocketTimeoutException -> subscriberOnListener.onError(-1001, "网络超时，请检查您的网络状态")
            is ConnectException -> subscriberOnListener.onError(-1002, "网络链接中断，请检查您的网络状态")
            is ExceptionApi -> subscriberOnListener.onError((e as ExceptionApi).code, (e as ExceptionApi).msg)
            else -> subscriberOnListener.onError(-1003, "未知错误:" + e.message)
        }
    }
}