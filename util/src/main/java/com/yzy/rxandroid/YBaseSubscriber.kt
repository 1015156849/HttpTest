package com.yzy.rxandroid

import android.accounts.NetworkErrorException
import android.annotation.SuppressLint
import com.google.gson.Gson
import com.yzy.http.BaseJsonObjectResult
import com.yzy.log.Log
import io.reactivex.Observer

import io.reactivex.disposables.Disposable



import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @packageName com.yzy.rxandroid
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/19
 * @description
 */
 abstract class YBaseSubscriber<T> : Observer<BaseJsonObjectResult<T>> {



    override fun onComplete() {
        closeLoading()
        onRequestEnd()
    }

    override fun onNext(t: BaseJsonObjectResult<T>) {
        Log.i("网络请求返回的数据 = "+Gson().toJson(t))
        if (t.success) {

            try {
                onSuccess(t)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                onFailure(t)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onSubscribe(d: Disposable) {
        if(!d.isDisposed){
            showLoading()
            onRequestStart()
        }
    }


    @SuppressLint("NewApi")
    override fun onError(e: Throwable) {
//        Log.w("onError: ");这里可以打印错误信息
        onRequestEnd()
        try {
            if (e is ConnectException
                || e is TimeoutException
                || e is NetworkErrorException
                || e is UnknownHostException
            ) {
                onErrorException(e, true)
            } else {
                onErrorException(e, false)
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    abstract fun onSuccess(t: BaseJsonObjectResult<T>)

    /**
     * @packageName com.yzy.rxandroid
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/20
     * @description 请求成功，但是success是失败
     * @params [t]
     * @return
     */
    abstract fun onFailure(t: BaseJsonObjectResult<T>)


    /**
     * @packageName com.yzy.rxandroid
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/20
     * @description 请求出现异常或者没有网络
     * @params [e, isNetWorkError]
     * @return
     */
    abstract fun onErrorException(e: Throwable, isNetWorkError: Boolean)
    private fun onRequestStart() {}
    private fun onRequestEnd() {}
    abstract fun showLoading()
    abstract fun closeLoading()
}