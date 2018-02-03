package com.lin.linreader.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.lin.linreader.R
import com.lin.linreader.adapter.HeaderViewRecyclerAdapter
import com.lin.linreader.adapter.ZhihuDailyAdapter
import com.lin.linreader.base.BaseFragment
import com.lin.linreader.bean.Stories
import com.lin.linreader.bean.ZhiHuDailyBean
import com.lin.linreader.http.HttpSubscriber
import com.lin.linreader.http.SubscriberOnListener
import com.lin.linreader.http.ZhiHuApi
import com.lin.linreader.utils.DateUtils
import com.lin.linreader.widget.EndlessRecyclerOnScrollListener
import com.lin.linreader.widget.GridItemDividerDecoration
import kotlinx.android.synthetic.main.load_more_foot_layout.*
import kotlinx.android.synthetic.main.zhihufragment.*

/**
 * Created by lin on 2018/2/2.
 */
class ZhiHuFragment : BaseFragment() {
    var page: Int = -1
    private lateinit var footlayout: LinearLayout
    private var storiesList = mutableListOf<Stories>()
    private var zhiHuDailyList = mutableListOf<ZhiHuDailyBean>()
    var zhihuDailyAdapter: ZhihuDailyAdapter? = null
    var headerViewRecyclerAdapter: HeaderViewRecyclerAdapter? = null
    override fun layoutId(): Int {
        return R.layout.zhihufragment
    }

    override fun initUi() {
        zhiHuRecycle.setHasFixedSize(true)
        zhiHuRecycle.layoutManager = LinearLayoutManager(mContext)
        zhiHuRecycle.addItemDecoration(GridItemDividerDecoration(mContext, R.dimen.divider_height, R.color.divider))
        zhiHuRecycle.itemAnimator = DefaultItemAnimator()
        footlayout = LayoutInflater.from(mContext).inflate(R.layout.load_more_foot_layout, zhiHuRecycle, false) as LinearLayout
        zhihuDailyAdapter = ZhihuDailyAdapter(mContext, storiesList)
        headerViewRecyclerAdapter = HeaderViewRecyclerAdapter(zhihuDailyAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        zhiHuRecycle.adapter = headerViewRecyclerAdapter
        createFootLayout()
        refresh()
        loadMore()
    }

    override fun initListener() {
        swipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            footlayout.visibility = View.GONE
            page = 0
            initData()
        })
    }

    override fun initData() {
        if (page == 0) {
            storiesList.clear()
            zhiHuDailyList.clear()
        }
        var date = DateUtils.timeyyyyMMdd
        if (zhiHuDailyList.size != 0) {
            date = zhiHuDailyList[zhiHuDailyList.size - 1].date
        }
        ZhiHuApi.getInstance(1)!!.getZhiHuListInfo(date, HttpSubscriber(object : SubscriberOnListener<ZhiHuDailyBean> {
            override fun onSucceed(zhiHuDailyBean: ZhiHuDailyBean) {
                zhiHuDailyList.add(zhiHuDailyBean)
                storiesList.addAll(zhiHuDailyBean.stories)
                zhihuDailyAdapter!!.update(storiesList)
                if (page == 0) {
                    refreshFinish()
                }
            }

            override fun onError(code: Int, msg: String) {
                if (page == 0) {
                    refreshFinish()
                }
                if (storiesList.size == 0) {
                    footlayout.visibility = View.GONE
                    load_pro.visibility = View.GONE
                } else {
                    AlreadyLoadAllData()

                }
            }
        }, mContext))
    }

    private fun createFootLayout() {
        headerViewRecyclerAdapter!!.addFooterView(footlayout)
        footlayout.visibility = View.GONE
    }

    private fun loadMore() {
        zhiHuRecycle.addOnScrollListener(object : EndlessRecyclerOnScrollListener(zhiHuRecycle.layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                footlayout.visibility = View.VISIBLE
                page++
                initData()
            }

        })
    }

    private fun refresh() {
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.isRefreshing = true

    }

    private fun refreshFinish() {

        swipeRefresh.post({ swipeRefresh.isRefreshing = false })
    }

    fun AlreadyLoadAllData() {
        footlayout.visibility = View.VISIBLE
        load_pro.visibility = View.INVISIBLE
        load_tv.visibility = View.VISIBLE
        load_tv.text = "已加载全部"
    }
}