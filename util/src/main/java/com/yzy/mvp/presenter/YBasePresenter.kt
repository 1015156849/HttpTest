package com.yzy.mvp.presenter

import android.content.Context
import com.yzy.base.YBaseActivity


import com.yzy.mvp.model.YBaseModel
import java.lang.reflect.Type
import kotlin.reflect.KClass

/**
 * @packageName com.yzy.mvp.presenter
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/18
 * @description 数据桥(处理数据)
 */
open class YBasePresenter {
    //当前页面的数据model
    private lateinit var mBindViewData: YBaseModel

    fun BindMVP(mContext: Context) {
        if (mContext is YBaseActivity<*, *>) {
            mBindViewData = mContext.mViewModel as YBaseModel
        }
    }

}