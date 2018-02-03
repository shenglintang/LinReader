package com.lin.linreader.http

import com.lin.linreader.bean.ZhiHuDailyBean
import rx.Subscriber

/**
 * Created by lin on 2018/2/2.
 */

class ZhiHuApi(code: Int) : BaseApi() {
    var userService: ZhiHuService

    init {
        userService = MyHttpMethod.getInstance(code)!!.createApi(ZhiHuService::class.java)
    }
    /*-------------------------------------获取的方法-------------------------------------*/

    fun getZhiHuListInfo(date: String, subscriber: Subscriber<ZhiHuDailyBean>) {
        val observable = userService.getZhiHuList(date)
                .map(BaseApi.HttpResultFunc())

        toSubscribe(observable, subscriber)
    }

    companion object {
        var mZhiHuApi: ZhiHuApi? = null

        fun getInstance(code: Int): ZhiHuApi? {
            when (code) {
                1 -> {
                    if (mZhiHuApi == null) {
                        synchronized(ZhiHuApi::class.java) {
                            if (null == mZhiHuApi)
                                mZhiHuApi = ZhiHuApi(code)
                        }
                    }
                    return mZhiHuApi
                }
            }
            return null
        }
    }


}

