//package com.lin.linreader.http
//
//import com.lin.linreader.bean.Stories
//import com.lin.linreader.bean.ZhiHuDailyBean
//import rx.Observable
//import rx.Subscriber
//
///**
// * Created by lin on 2018/2/2.
// */
//class ZhiHuApi  {
//    class ZhiHuApi constructor(code: Int) {
//         val zhiHuService: ZhiHuService
//         val baseApi= BaseApi()
//
//        init {
//            zhiHuService = MyHttpMethod.getInstance(code)!!.createApi(ZhiHuService::class.java)
//        }
//
//        fun getZhiHuListInfo(date: String, subscriber: Subscriber<List<Stories>>) {
//            val observable = zhiHuService.getZhiHuList(date).map(BaseApi.HttpResultFunc<ZhiHuDailyBean>())
//            baseApi.toSubscribe(observable, subscriber)
//        }
//    }
//
//
//}