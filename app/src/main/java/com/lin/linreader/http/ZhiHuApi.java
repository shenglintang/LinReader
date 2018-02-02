package com.lin.linreader.http;

import com.lin.linreader.bean.Stories;
import com.lin.linreader.bean.ZhiHuDailyBean;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by lin on 2018/2/2.
 */

public class ZhiHuApi {
    public static ZhiHuApi mZhiHuApi;
    public ZhiHuService userService;
    private BaseApi mBaseApi;

    public ZhiHuApi(int code) {
        mBaseApi = new BaseApi();
        userService = MyHttpMethod.Companion.getInstance(1).createApi(ZhiHuService.class);
    }

    public static ZhiHuApi getInstance(int code) {
        switch (code) {
            case 1:
                if (mZhiHuApi == null) {
                    synchronized (ZhiHuApi.class) {
                        if (null == mZhiHuApi)
                            mZhiHuApi = new ZhiHuApi(code);
                    }
                }
                return mZhiHuApi;
        }
        return null;
    }
    /*-------------------------------------获取的方法-------------------------------------*/

    public void getZhiHuListInfo(String date, Subscriber<List<Stories>> subscriber) {
        Observable observable = userService.getZhiHuList(date)
                .map(new BaseApi.HttpResultFunc<ZhiHuDailyBean>());

        mBaseApi.toSubscribe(observable, subscriber);
    }


}

