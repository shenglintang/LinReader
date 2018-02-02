package com.lin.linreader.http

import com.lin.linreader.bean.ZhiHuDailyBean
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by lin on 2018/2/2.
 */
interface ZhiHuService {
    @GET("/api/4/news/before/{date}")
    fun getZhiHuList(@Path("date") date: String): Observable<ZhiHuDailyBean>
}