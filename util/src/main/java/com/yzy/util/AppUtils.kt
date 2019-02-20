package com.yzy.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.yzy.YUtil
import com.yzy.log.Log

/**
 * @author 杨振宇 1015156849@qq.com
 * @packageName com.yzy.Util
 * @createTime 2019/2/13
 * @description
 */

class AppUtils {

    companion object {
        /**
         * 获取应用程序名称
         */

        fun getAppName(context: Context): String? {
            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0
                )
                val labelRes = packageInfo.applicationInfo.labelRes
                return context.resources.getString(labelRes)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        /**
         * [获取应用程序版本名称信息]
         * @param context
         * @return 当前应用的版本名称
         */

        fun getVersionName(context: Context): String? {
            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0
                )
                return packageInfo.versionName
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }


        /**
         * [获取应用程序版本名称信息]
         * @param context
         * @return 当前应用的版本名称
         */

        fun getVersionCode(context: Context): Long {
            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0
                )
                return packageInfo.longVersionCode
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return 0L
        }


        /**
         * [获取应用程序版本名称信息]
         * @param context
         * @return 当前应用的版本名称
         */

        fun getPackageName(context: Context): String? {

            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0
                )
                return packageInfo.packageName
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }


        /**
         * 获取图标 bitmap
         * @param context
         */

        fun getBitmap(context: Context): Bitmap {
            var packageManager: PackageManager? = null
            var applicationInfo: ApplicationInfo? = null
            try {
                packageManager = context.applicationContext
                    .packageManager
                applicationInfo = packageManager!!.getApplicationInfo(
                    context.packageName, 0
                )
            } catch (e: PackageManager.NameNotFoundException) {
                applicationInfo = null
            }

            //xxx根据自己的情况获取drawable
            val d = packageManager!!.getApplicationIcon(applicationInfo)
            val bd = d as BitmapDrawable
            return bd.bitmap
        }

        /**
         * @packageName com.yzy.util
         * @author 杨振宇 1015156849@qq.com
         * @createTime 2019/2/13
         * @description 判断当前应用是否是debug状态
         * @params [context]
         * @return Boolean
         */
        fun isApkInDebug(context: Context): Boolean {
            return try {
                val info = context.applicationInfo
                info.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
            } catch (e: Exception) {
                false
            }

        }
    }
}

