package com.rongcheng.hi.log

import android.app.Application
import com.rongcheng.hilog.HiLog
import com.rongcheng.hilog.timer.Timber
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog

/** A tree which logs important information for crash reporting.  */
class CrashReportingTree(context: Application) : Timber.Tree() {
//        val cachePath: String
//        val SDCARD: String = Environment.getExternalStorageDirectory().absolutePath
//        val logPath: String = SDCARD + "/marssample/log"

    init {
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        val cachePath = "${context.filesDir}/marssample/xlog"
        val xlog = Xlog()
        Log.setLogImp(xlog)
        Log.setConsoleLogOpen(false)
        Log.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, "", cachePath, "hahh", 0);
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == android.util.Log.ERROR || priority == android.util.Log.WARN || priority == android.util.Log.ASSERT || priority == android.util.Log.INFO
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (t != null) {
            if (priority == android.util.Log.ERROR) {
                Log.e(tag ?: "Xlog", message)
            } else if (priority == android.util.Log.WARN) {
                Log.w(tag ?: "Xlog", message)
            }else if (priority==android.util.Log.WARN){
                Log.i(tag ?: "Xlog", message)
            }
        }
//        if (priority == android.util.Log.INFO) {
//            HiLog.it(tag ?: "HiLogRelease", message)
//        }
    }
}