package com.rongcheng.hilog.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.util.TypedValue
import android.view.WindowManager

class HiDisplayUtil {
    companion object {
        fun dp2px(dp: Float, resources: Resources): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.displayMetrics
            ).toInt()
        }

        fun getDisplayWithInPx(context: Context):Int{
            val wm:WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            if (wm!=null){
                val display=wm.defaultDisplay
                val size= Point()
                display.getSize(size)
                return size.x
            }
            return 0
        }
        fun getDisplayHeightInPx(context: Context):Int{
            val wm:WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            if (wm!=null){
                val display=wm.defaultDisplay
                val size= Point()
                display.getSize(size)
                return size.y
            }
            return 0
        }

    }
}