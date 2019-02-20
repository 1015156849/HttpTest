package com.yzy.base

import androidx.appcompat.app.AppCompatActivity
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.widget.Toast
import com.yzy.callback.PermissionCallBack
import com.yzy.screen.Screen
import com.yzy.bar.StatusBarUtil
import com.yzy.mvp.presenter.YBasePresenter
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


import retrofit2.Call
import java.util.*


/**
 * @packageName com.yzy.Base
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/12
 * @description 用来继承使用的Activity
 */
abstract class YBaseActivity<PresenterClass, ViewModelClass>(
    var mPresenter: PresenterClass,
    var mViewModel: ViewModelClass
) : AppCompatActivity() {

    private lateinit var httpQueue: ArrayList<Call<Any>>
    private lateinit var permissionCallBack: PermissionCallBack

    /**
     * @packageName com.yzy.Base
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/12
     * @description 初始化页面
     * @params [savedInstanceState]
     * @return
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMVP()
        val metric = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metric)
        Screen.mScreenWidth = metric.widthPixels
        Screen.mScreenHeight = metric.heightPixels

        if (!TextUtils.isEmpty(android.os.Build.BRAND)) {
            if (!StatusBarUtil.FlymeSetStatusBarLightMode(window, false)) {
                YBaseApplication.instance.virtualKeyHeight = 0
            }
        }
        initSystemBarTint(Color.BLUE)

    }

    /**
     * @packageName com.yzy.Base
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/12
     * @description 设置状态栏颜色
     * @params [colorInt]
     * @return
     */
    private val initSystemBarTint: (Int) -> Unit =
        { colorInt -> StatusBarUtil.setStatusBarColor(getActivityContext(), colorInt) }


    val getActivityContext: () -> YBaseActivity<PresenterClass, ViewModelClass> = { this }

    /*********************权限部分开始****************/
    internal fun setPermissionsCallBack(callback: PermissionCallBack) {
        this.permissionCallBack = callback
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionCallBack.success()
        } else {
            //提示用户权限未授予
            permissionCallBack.fail()
            Toast.makeText(this@YBaseActivity, "权限未授予", Toast.LENGTH_SHORT).show()
        }
    }
    /*********************权限部分结束****************/
    /*********************绑定数据和数据处理层开始****************/
    fun initMVP() {
        if (mPresenter is YBasePresenter) {
            val presenter = mPresenter as YBasePresenter
            presenter.BindMVP(this@YBaseActivity)
        }
    }
    /*******************绑定数据和数据处理层结束*********************/


}