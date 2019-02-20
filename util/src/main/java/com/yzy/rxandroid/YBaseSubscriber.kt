package com.yzy.rxandroid

import android.accounts.NetworkErrorException
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
        showLoading()
        onRequestStart()
    }

    override fun onNext(t: BaseJsonObjectResult<T>) {
        closeLoading()
        onRequestEnd()
        if (t.success) {
            try {
                onSuccess(t)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                onCodeError(t)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onSubscribe(d: Disposable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onError(e: Throwable) {
//        Log.w("onError: ");这里可以打印错误信息
        onRequestEnd()
        try {
            if (e is ConnectException
                || e is TimeoutException
                || e is NetworkErrorException
                || e is UnknownHostException
            ) {
                onFailure(e, true)
            } else {
                onFailure(e, false)
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
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    private fun onCodeError(t: BaseJsonObjectResult<T>) {
    }


    abstract fun onFailure(e: Throwable, isNetWorkError: Boolean)
    private fun onRequestStart() {}
    private fun onRequestEnd() {}
    abstract fun showLoading()
    abstract fun closeLoading()
}