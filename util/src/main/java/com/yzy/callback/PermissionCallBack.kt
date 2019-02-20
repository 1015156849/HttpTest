package com.yzy.callback

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
    fun success() {}
    /**
     * @packageName com.yzy.callback
     * @author 杨振宇 1015156849@qq.com
     * @createTime 2019/2/13
     * @description 获取权限失败后的处理（可以不重写，有需要就重写）
     * @params []
     * @return
     */
    fun fail() {}
}