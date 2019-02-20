package com.ui.myapplication

import com.ui.myapplication.Base.LoginReq
import com.yzy.http.BaseJsonObjectResult
import com.yzy.rxandroid.YBaseSubscriber
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

/**
 * @packageName com.ui.myapplication
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/19
 * @description
 */
interface DoLogin {

    /**
     * @packageName com.ui.myapplication
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/20
     * @description
     * @params [request]
     * @return
     */
    @POST("login")
    fun login(@Body request: LoginReq): Observable<BaseJsonObjectResult<String>>
}