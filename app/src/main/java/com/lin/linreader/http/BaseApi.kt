package com.lin.linreader.http

import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * Created by lin on 2018/2/2.
 */
open class BaseApi {
     fun <T> toSubscribe(o: Observable<T>, s: Subscriber<T>) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s)
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     * Func1(I,O) 输入和输出
    </T> */
     class HttpResultFunc<T> : Func1<T, T> {

        override fun call(T: T): T {
            return T
        }
    }
}