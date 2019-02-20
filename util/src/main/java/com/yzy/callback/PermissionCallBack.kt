package com.yzy.callback

import com.yzy.log.Log

/**
 * @packageName com.yzy.callback
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/13
 * @description
 */
interface PermissionCallBack {
    /**
     * @packageName com.yzy.callback
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/13
     * @description 获取权限成功后的处理（可以不重写，有需要就重写）
     * @params []
     * @return
     */
    fun success() {   Log.i("成功获取到权限了")}
    /**
     * @packageName com.yzy.callback
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/13
     * @description 获取权限失败后的处理（可以不重写，有需要就重写）
     * @params []
     * @return
     */
    fun fail() {   Log.i("获取权限失败")}
}