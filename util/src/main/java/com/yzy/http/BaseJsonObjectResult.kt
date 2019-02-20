package com.yzy.http

/**
 * @packageName com.ui.myapplication
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/15
 * @description
 */
open class BaseJsonObjectResult<T>(
    var success: Boolean,
    var data: T,
    var error: String?,
    var errorDesc: String?
)