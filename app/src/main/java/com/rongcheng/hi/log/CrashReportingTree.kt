package com.rongcheng.hi.log

import android.app.Application
import com.rongcheng.hilog.timer.Timber
import com.tencent.mars.xlog.Log
import com.tencent.mars.xlog.Xlog
import java.io.File

/** A tree which logs important information for crash reporting.  */
class CrashReportingTree(context: Application) : Timber.Tree() {
//        val cachePath: String
//        val SDCARD: String = Environment.getExternalStorageDirectory().absolutePath
//        val logPath: String = SDCARD + "/marssample/log"

    private val defaultTag = "XLog"

    init {
        android.util.Log.i(defaultTag, "crash init")
        System.loadLibrary("c++_shared")
        System.loadLibrary("marsxlog")
        val crashPath = "${context.filesDir}/marssample/xlog"
        val xLogDir = File(crashPath)
        if (xLogDir.exists()) {
            if (xLogDir.isDirectory) {
                xLogDir.list()?.forEach {
                    val fileName = "$crashPath/$it"
                    android.util.Log.i("frc", "file path $fileName ")
                    val file = File(fileName)
                    val content = file.readText()
                    android.util.Log.i("frc", content)
                }
            }
            android.util.Log.i("frc", "xLog crash file create ok")
        }
        Log.setLogImp(Xlog())
        Log.setConsoleLogOpen(false)
        Log.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, "", crashPath, "hah", 0)
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority == android.util.Log.ERROR || priority == android.util.Log.WARN || priority == android.util.Log.ASSERT || priority == android.util.Log.INFO
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (t != null) {
            when (priority) {
                android.util.Log.ERROR -> {
                    Log.e(tag ?: defaultTag, "$message ${t.localizedMessage}")
                }
                android.util.Log.WARN -> {
                    Log.w(tag ?: defaultTag, "$message ${t.localizedMessage}")
                }
                android.util.Log.INFO -> {
                    Log.i(tag ?: defaultTag, "$message ${t.localizedMessage}")
                }
            }
        } else {
            Log.i(tag ?: defaultTag, message)
        }
    }
}