package com.lin.linreader.activity

import android.view.View
import com.lin.linreader.R
import com.lin.linreader.base.BaseActivity
import com.lin.linreader.fragment.ZhiHuFragment
import com.lin.linreader.utils.LogUtil
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int {
        return R.layout.activity_home
    }

    override fun initUi() {
        initToolBar()
        var zhiHuFragment = ZhiHuFragment()
        zhiHuFragment.initData()
    }

    override fun initOnClick() {
        fab.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> LogUtil.e(v.id.toString())
        }
    }

    private fun initToolBar() {
        mToolbar.title = ""
        toolbarTitle.text = "新闻"
        setSupportActionBar(mToolbar)
        val mActionBar = supportActionBar
        mActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
