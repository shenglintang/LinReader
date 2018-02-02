package com.lin.linreader.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by lin on 2018/2/2.
 */
abstract class BaseFragment : Fragment() {
    private lateinit var mRootView: View
    protected open lateinit var mContext: Context
    protected open lateinit var mActivity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mContext = getContext()
            mActivity = getActivity()
            mRootView = inflater.inflate(layoutId(), container, false)

        }
        return mRootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initOnClick()
        initData()
    }

    open fun initData() {}
    abstract fun layoutId(): Int
    abstract fun initUi()
    abstract fun initOnClick()
}
