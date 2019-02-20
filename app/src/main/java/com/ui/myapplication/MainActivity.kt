package com.ui.myapplication

import android.Manifest
import android.app.Activity
import android.os.Bundle
import com.ui.myapplication.Base.LoginModel
import com.ui.myapplication.Base.LoginPresenter
import com.yzy.base.YBaseActivity
import com.yzy.YUtil
import com.yzy.callback.PermissionCallBack
import com.yzy.log.Log
import com.yzy.toast.Toast


/**
 * @packageName com.ui.myapplication
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/12
 * @description
 */
class MainActivity
//    :Activity()
    : YBaseActivity<LoginPresenter, LoginModel>(LoginPresenter(), LoginModel())
{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSystemBarTint(R.color.colorPrimary)
//        YUtil.instance.getPermission(
//            this,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            object : PermissionCallBack {
//                override fun success() {
//                    super.success()
//                    Log.i("成功获取到权限了")
//                    Toast.show(getActivityContext(), "恭喜你拿到了权限", 1)
//                }
//            })
//
//        mPresenter.doLogin("1","2")


    }


}


