package com.ui.myapplication.Base

import android.annotation.SuppressLint
import com.ui.myapplication.DoLogin
import com.yzy.YUtil
import com.yzy.http.BaseJsonObjectResult
import com.yzy.mvp.presenter.YBasePresenter
import com.yzy.rxandroid.YBaseSubscriber

/**
 * @packageName com.ui.myapplication.Base
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/18
 * @description
 */
class LoginPresenter :
        YBasePresenter() {

    @SuppressLint("CheckResult")
            /**
             * @packageName com.ui.myapplication.Base
             * @author 杨振宇 1015156849@qq.com
             * @createTime 2019/2/18
             * @description
             * @params [tel, pwd]
             * @return
             */
    fun doLogin(tel: String, pwd: String) {

        var doLogin = YUtil.instance.http?.create(DoLogin::class.java)

        doLogin!!.login(tel, pwd).compose(YUtil.instance.requestSetThread()).subscribe(object : MyOb<String>() {
            override fun onFailure(e: Throwable, isNetWorkError: Boolean) {


            }

            override fun showLoading() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun closeLoading() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSuccess(t: BaseJsonObjectResult<String>) {


            }

        })
    }

}





