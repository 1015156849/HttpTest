package com.yzy.log

import android.util.Log
import com.yzy.YUtil
import com.yzy.base.YBaseApplication
import com.yzy.util.AppUtils

/**
 * @packageName com.yzy.Log
 * @author 杨振宇 1015156849@qq.com
 * @createTime 2019/2/13
 * @description
 */
class Log {
    companion object {

        /**
         * @packageName com.yzy.log
         * @author 杨振宇 1015156849@qq.com
         * @createTime 2019/2/13
         * @description log.i
         * @params [tips]
         * @return
         */
        fun i(tips: String) {
            if (YUtil.instance.isDEBUG) {
                Log.i(
                    YUtil.instance.mTAG,
                    "==================>LOG \n " + tips + "\n <=================="
                )
            }
        }

        /**
         * @packageName com.yzy.log
         * @author 杨振宇 1015156849@qq.com
         * @createTime 2019/2/13
         * @description log.w
         * @params [tips]
         * @return
         */
        fun w(tips: String) {
            if (YUtil.instance.isDEBUG) {
                Log.w(
                    YUtil.instance.mTAG,
                    "==================>LOG \n " + tips + "\n <=================="
                )
            }
        }

        /**
         * @packageName com.yzy.log
         * @author 杨振宇 1015156849@qq.com
         * @createTime 2019/2/13
         * @description log.e
         * @params [tips]
         * @return
         */
        fun e(tips: String) {
            if (YUtil.instance.isDEBUG) {
                Log.e(
                    YUtil.instance.mTAG,
                    "==================>LOG \n " + tips + "\n <=================="
                )
            }
        }
    }

}