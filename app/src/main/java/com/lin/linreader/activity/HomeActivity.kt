package com.lin.linreader.activity

import android.view.View
import com.lin.linreader.R
import com.lin.linreader.base.BaseActivity
import com.lin.linreader.fragment.ZhiHuFragment
import com.lin.linreader.utils.SnackbarUtil
import kotlinx.android.synthetic.main.activity_home.*



class HomeActivity : BaseActivity(), View.OnClickListener {
    override fun layoutId(): Int {
        return R.layout.activity_home
    }

    override fun initUi() {
        initToolBar()
        val zhiHuFragment = ZhiHuFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, zhiHuFragment).show(zhiHuFragment).commit()
    }

    override fun initOnClick() {
        fab.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> SnackbarUtil.showMessage(coordinatorLayout, "sss")
        }
    }

    private fun initToolBar() {
        mToolbar.title = ""
        toolbarTitle.text = "新闻"
        setSupportActionBar(mToolbar)
        val mActionBar = supportActionBar
//        mActionBar?.setDisplayHomeAsUpEnabled(true)//是否显示返回键
    }

}
