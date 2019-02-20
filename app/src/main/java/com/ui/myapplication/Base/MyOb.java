package com.ui.myapplication.Base;

import com.yzy.http.BaseJsonObjectResult;
import com.yzy.log.Log;
import com.yzy.rxandroid.YBaseSubscriber;

import org.jetbrains.annotations.NotNull;

/**
 * @author 杨振宇 1015156849@qq.com
 * @packageName com.ui.myapplication.Base
 * @createTime 2019/2/19
 * @description
 */
abstract class MyOb<T> extends YBaseSubscriber<T> {


    @Override
    public void showLoading() {
        Log.Companion.i("开始网络请求");
    }

    @Override
    public void closeLoading() {
        Log.Companion.i("结束网络请求");
    }

    @Override
    public void onErrorException(@NotNull Throwable e, boolean isNetWorkError) {
        Log.Companion.i("是否是未开启网络 "+isNetWorkError);
        if(!isNetWorkError) { e.printStackTrace();}

    }


}
