package com.yzy.base

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.yzy.YUtil
import com.yzy.log.Log
import java.util.*

/**
 * @packageName com.yzy.Base
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/12
 * @description
 */
open class YBaseApplication : Application() {
    //状态栏的高度
    var statusHeight: Int = 0
    //虚拟按键的高度
    var virtualKeyHeight = 0
    //屏幕的高度aaa
    var pingmuHeight = 0
    // Activity栈
    private var activityStack: Stack<AppCompatActivity>? = null

    companion object {
        /**双重校验锁式*/
        val instance: YBaseApplication by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Log.i("初始化YBaseApplication")
            YBaseApplication()
        }
    }

    override fun onCreate() {
        super.onCreate()
        /**初始化工具类，只影响Toast和Log的正常使用*/
        YUtil.instance.init(this).initHttp()
    }



}