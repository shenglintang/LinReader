package com.lin.linreader.fragment

import com.lin.linreader.R
import com.lin.linreader.base.BaseFragment
import com.lin.linreader.bean.Stories
import com.lin.linreader.http.HttpSubscriber
import com.lin.linreader.http.SubscriberOnListener
import com.lin.linreader.http.ZhiHuApi
import com.lin.linreader.utils.DateUtils
import com.lin.linreader.utils.LogUtil

/**
 * Created by lin on 2018/2/2.
 */
class ZhiHuFragment : BaseFragment() {

    override fun layoutId(): Int {
        return R.layout.zhihufragment
    }

    override fun initUi() {

    }

    override fun initOnClick() {
    }

    override fun initData() {
        LogUtil.e("initData")
        ZhiHuApi(1).getZhiHuListInfo(DateUtils.timeyyyyMMdd, HttpSubscriber<List<Stories>>(
                object : SubscriberOnListener<List<Stories>> {
                    override fun onSucceed(data: List<Stories>) {
                        LogUtil.e(data.toString())
                    }

                    override fun onError(code: Int, msg: String) {
                    }

                }
                , mContext))
    }
}