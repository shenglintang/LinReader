package com.lin.linreader.http

/**
 * Created by lin on 2018/2/2.
 */
interface SubscriberOnListener<T> {

    fun onSucceed(data: T)
    fun onError(code: Int, msg: String)
}