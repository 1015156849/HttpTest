package com.yzy.bar

import android.annotation.TargetApi
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.yzy.lib.SystemBarTintManager

/**
 * @packageName com.yzy.StatusBarUtil
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/12
 * @description
 */
class StatusBarUtil {
    companion object {
        /**
         * 修改状态栏为全透明
         * @param activity
         */
        @TargetApi(19)
        private fun transparencyBar(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window.statusBarColor = Color.TRANSPARENT
            } else
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    activity.window.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    )

                }

        }

        /**
         * 修改状态栏颜色，支持4.4以上版本
         * @param activity
         * @param colorId
         */
        fun setStatusBarColor(activity: Activity, colorId: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window.statusBarColor = colorId
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //使用SystemBarTint库使4.4版本状态栏变色，需要先将状态栏设置为透明
                transparencyBar(activity)
                var tintManager = SystemBarTintManager(activity)
                tintManager.isStatusBarTintEnabled = true
                try {
                    tintManager.setStatusBarTintResource(colorId)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

        /**
         * 设置状态栏黑色字体图标，
         * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
         * @param activity
         * @return 1:MIUUI 2:Flyme 3:android6.0
         */
        fun StatusBarLightMode(activity: Activity): Int {
            var result = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (MIUISetStatusBarLightMode(activity.window, true)) {
                    result = 1
                } else if (FlymeSetStatusBarLightMode(activity.window, true)) {
                    result = 2
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    result = 3
                }
            }
            return result
        }

        /**
         * 已知系统类型时，设置状态栏黑色字体图标。
         * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
         * @param activity
         * @param type 1:MIUUI 2:Flyme 3:android6.0
         */
        fun StatusBarLightMode(activity: Activity, type: Int) {
            if (type == 1) {
                MIUISetStatusBarLightMode(activity.window, true)
            } else if (type == 2) {
                FlymeSetStatusBarLightMode(activity.window, true)
            } else if (type == 3) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }

        }

        /**
         * 清除MIUI或flyme或6.0以上版本状态栏黑色字体
         */
        fun StatusBarDarkMode(activity: Activity, type: Int) {
            if (type == 1) {
                MIUISetStatusBarLightMode(activity.window, false)
            } else if (type == 2) {
                FlymeSetStatusBarLightMode(activity.window, false)
            } else if (type == 3) {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }

        }


        /**
         * 设置状态栏图标为深色和魅族特定的文字风格
         * 可以用来判断是否为Flyme用户
         * @param window 需要设置的窗口
         * @param dark 是否把状态栏字体及图标颜色设置为深色
         * @return  boolean 成功执行返回true
         */
        fun FlymeSetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
            var result = false
            if (window != null) {
                try {
                    val lp = window.attributes
                    val darkFlag = WindowManager.LayoutParams::class.java
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                    val meizuFlags = WindowManager.LayoutParams::class.java
                        .getDeclaredField("meizuFlags")
                    //                darkFlag.setAccessible(true);
                    //                meizuFlags.setAccessible(true);
                    //                int bit = darkFlag.getInt(null);
                    //                int value = meizuFlags.getInt(lp);
                    //                if (dark) {
                    //                    value |= bit;
                    //                } else {
                    //                    value &= ~bit;
                    //                }
                    //                meizuFlags.setInt(lp, value);
                    //                window.setAttributes(lp);
                    result = true
                } catch (e: Exception) {

                }

            }
            return result
        }

        /**
         * 设置状态栏字体图标为深色，需要MIUIV6以上
         * @param window 需要设置的窗口
         * @param dark 是否把状态栏字体及图标颜色设置为深色
         * @return  boolean 成功执行返回true
         */
        fun MIUISetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
            var result = false
            if (window != null) {
                val clazz = window.javaClass
                try {
                    var darkModeFlag = 0
                    val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                    val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                    darkModeFlag = field.getInt(layoutParams)
                    val extraFlagField = clazz.getMethod(
                        "setExtraFlags",
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType
                    )
                    if (dark) {
                        extraFlagField.invoke(window, darkModeFlag, darkModeFlag)//状态栏透明且黑色字体
                    } else {
                        extraFlagField.invoke(window, 0, darkModeFlag)//清除黑色字体
                    }
                    result = true
                } catch (e: Exception) {

                }

            }
            return result
        }
    }
}