package com.yzy.toast

import android.content.Context
import android.widget.Toast
import com.yzy.YUtil
import com.yzy.base.YBaseActivity
import com.yzy.base.YBaseApplication

/**
 * @packageName com.yzy.toast
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/13
 * @description Toast工具类
 */
class Toast {
    companion object {
        /**
         * @packageName com.yzy.toast
         * @author 杨振宇 1015156849@qq.com
         * @createTime 2019/2/13
         * @description toast.makeText
         * @params [tips, time]
         * @return
         */
        fun show(context: YBaseActivity<*,*>, tips: String, time: Int) {
            if (YUtil.instance.isDEBUG) Toast.makeText(context, tips, time).show()
        }
    }
}