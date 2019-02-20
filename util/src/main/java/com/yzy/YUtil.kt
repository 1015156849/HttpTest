package com.yzy

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.yzy.base.YBaseActivity

import com.yzy.callback.PermissionCallBack
import com.yzy.log.Log
import com.yzy.util.AppUtils

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

import com.google.gson.GsonBuilder


import java.util.concurrent.TimeUnit
import com.yzy.base.YBaseApplication
import com.yzy.http.HttpsUtils
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*


import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


import java.io.File
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession


/**
 * @packageName com.yzy
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/12
 * @description 主入口
 */
class YUtil {
    var mTAG: String? = "YUtil"
    var isDEBUG: Boolean = false
    var http: Retrofit? = null


    private val READ_TIME_OUT: Long = 30000
    private val CONNECT_TIME_OUT: Long = 30000

    /**获取权限*/
    private val GET_PERMISSION: Int = 101

    var BASE_HTTP_URL = ""

    companion object {
        /**双重校验锁式*/
        val instance: YUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            YUtil()
        }
    }

    /**
     * @packageName com.yzy
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/15
     * @description 初始化TAG和DEBUG标识
     * @params [context]
     * @return
     */
    internal fun init(context: Context): YUtil {
        mTAG = AppUtils.getPackageName(context)
        isDEBUG = AppUtils.isApkInDebug(context)
        return this
    }


    /**
     * @packageName com.yzy
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/15
     * @description 初始化网络工具
     * @params []
     * @return
     */
    internal fun initHttp(): YUtil {

        Log.i("初始化网络工具")

//        //缓存
//        val cacheFile = File(YBaseApplication.instance.cacheDir, "cache")
//        //100Mb
//        val cache = Cache(cacheFile, 1024 * 1024 * 100)
        //增加头部信息
//        val headerInterceptor = Interceptor { chain ->
//            val build = chain.request().newBuilder()
//                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("DeviceType", "Android")
//                .addHeader("version", "" + AppUtils.getVersionName(YBaseApplication.instance))
//                .build()
//            chain.proceed(build)
//        }

//        val sslParams = HttpsUtils.getSslSocketFactory(null, null, null)
//        val okHttpClient = OkHttpClient.Builder()
////            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//            .addInterceptor(
//                 object :Interceptor{
//                     override fun intercept(chain: Interceptor.Chain): Response {
//                             val request = chain.request().newBuilder()
//                                 .addHeader("Accept", "Application/JSON").build()
//                             return chain.proceed(request)
//                     }
//                 })
//            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
//            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
//            .hostnameVerifier { hostname, session -> true }
//            .build()
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create()

        http = Retrofit.Builder()
//            .client(okHttpClient)
            .baseUrl("http://10.58.202.125:8081/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return this
    }

    /**
     * @packageName com.yzy
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/12
     * @description 传入需要的权限，自动判断是否已获取，并申请获取权限，一般来说都是会知道自己申请的是哪个具体权限
     * @params [mContext, list]
     * @return
     */
    fun getPermission(
        mContext: YBaseActivity<*, *>,
        mPermission: String,
        mCallback: PermissionCallBack
    ) {
        mContext.setPermissionsCallBack(mCallback)
        /**先进行权限检查，符合条件的就去申请*/
        if (ActivityCompat.checkSelfPermission(
                mContext,
                mPermission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.i("没有权限")
            return ActivityCompat.requestPermissions(mContext, arrayOf(mPermission), GET_PERMISSION)

        }
        Log.i("已获取权限")
        mCallback.success()

    }

    /*********************Http请求封装线程切换开始****************/
    fun <T> requestSetThread(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
    /*********************Http请求封装线程切换结束****************/

}



