package com.rongcheng.hi

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.rongcheng.hilog.HiLog
import com.rongcheng.hilog.HiLogConfig
import com.rongcheng.hilog.HiLogManager
import com.rongcheng.hilog.printer.console.HiConsolePrinter
import com.rongcheng.hilog.timer.HiLogDebugTree
import com.rongcheng.hilog.timer.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.init(object : HiLogConfig() {
            override fun injectJsonParser(): JsonParser {
                return object : JsonParser {
                    override fun toJson(src: Any): String {
                        return Gson().toJson(src)
                    }
                }
            }

            override fun getGlobalTag(): String {
                return "HiApp "
            }


            override fun stackTraceDepth(): Int {
                return 0
            }
        }, HiConsolePrinter())
        if (BuildConfig.DEBUG) {
            Timber.plant(HiLogDebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    /** A tree which logs important information for crash reporting.  */
    private class CrashReportingTree : Timber.Tree() {
        override fun isLoggable(tag: String?, priority: Int): Boolean {
            return priority == Log.ERROR || priority == Log.WARN || priority == Log.ASSERT || priority == Log.INFO
        }

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (t != null) {
                if (priority == Log.ERROR) {
//                   bugly
                } else if (priority == Log.WARN) {
//                   bugly
                }
            }
            if (priority == Log.INFO) {
                HiLog.it(tag ?: "HiLogRelease", message)
            }
        }
    }
}