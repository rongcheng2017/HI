package com.rongcheng.hilog

import android.util.Log
import androidx.annotation.IntDef

class HiLogType {
    companion object {
        const val V: Int = Log.VERBOSE
        const val D: Int = Log.DEBUG
        const val I: Int = Log.INFO
        const val W: Int = Log.WARN
        const val E: Int = Log.ERROR
        const val A: Int = Log.ASSERT
    }


    @IntDef(A, V, D, I, W, E)
    @Retention(AnnotationRetention.SOURCE)
    annotation class TYPE {}
}