package com.rongcheng.hi

import android.app.Application
import com.rongcheng.hi.log.AppDefaultHiLogConfig
import com.rongcheng.hi.log.CrashReportingTree
import com.rongcheng.hilog.HiLogManager
import com.rongcheng.hilog.printer.console.HiConsolePrinter
import com.rongcheng.hilog.timer.HiLogDebugTree
import com.rongcheng.hilog.timer.Timber


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.init(AppDefaultHiLogConfig, HiConsolePrinter())
        if (BuildConfig.DEBUG) {
            Timber.plant(HiLogDebugTree())
        } else {
            Timber.plant(CrashReportingTree(this))
        }
    }
}