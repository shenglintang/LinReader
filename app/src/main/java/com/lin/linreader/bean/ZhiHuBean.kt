package com.lin.linreader.bean

/**
 * Created by lin on 2018/2/2.
 */
data class Stories(var images: List<String>, var type: Int, var id: Int, var ga_prefix: String
                   , var title: String, var date: String)

data class ZhiHuDailyBean(var date: String, var stories: List<Stories>)