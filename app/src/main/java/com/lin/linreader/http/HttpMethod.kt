package com.lin.linreader.http


import com.lin.linreader.Constant
import com.lin.linreader.MyApplication
import com.lin.linreader.utils.LogUtil
import com.lin.linreader.utils.NetUtil
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.internal.http.HttpMethod
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class MyHttpMethod private constructor(url: String) {

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(genericClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
    fun <T> createApi(clazz: Class<T>): T {

        return retrofit.create(clazz)
    }
    companion object {

        var token = ""//请求时用户的Token
        private lateinit var retrofit: Retrofit
        private var instance_zhihu: MyHttpMethod? = null

        //获取单例
        fun getInstance(code: Int): MyHttpMethod? {
            when (code) {
                1 -> {
                    if (instance_zhihu == null) {

                        synchronized(HttpMethod::class.java) {
                            if (null == instance_zhihu) {
                                instance_zhihu = MyHttpMethod(Constant.ZHIHU_URL)
                            }
                        }
                    }
                    return instance_zhihu
                }
            }
            return null
        }


        fun genericClient(): OkHttpClient {
            //1.有网和没有网都是先读缓存
            //设置缓存路径
            LogUtil.e("MyApplication().instance == ${MyApplication().instance}")
            val httpCacheDirectory = File(MyApplication().instance.getExternalCacheDir().getAbsolutePath(), "responses")
            //设置缓存 10M
            val cache = Cache(httpCacheDirectory, (10 * 1024 * 1024).toLong())
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        var request = chain.request()
                                .newBuilder()
                                .addHeader("token", token)//添加token
                                .build()

                        if (!NetUtil.getNetworkIsConnected(MyApplication().instance)) {
                            request = request.newBuilder()
                                    .cacheControl(CacheControl.FORCE_CACHE)
                                    .build()
                        }

                        val response = chain.proceed(request)
                        if (NetUtil.getNetworkIsConnected(MyApplication().instance)) {
                            val maxAge = 0 * 60 // 有网络时 设置缓存超时时间0个小时
                            response.newBuilder()
                                    .addHeader("Cache-Control", "public, max-age=" + maxAge)
                                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                    .build()
                        } else {
                            val maxStale = 60 * 60 * 24 * 7 // 无网络时，设置超时为1周
                            response.newBuilder()
                                    .addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .removeHeader("Pragma")
                                    .build()
                        }
                        response
                    }.addInterceptor(logging).cache(cache)
                    .build()
        }
    }

}
