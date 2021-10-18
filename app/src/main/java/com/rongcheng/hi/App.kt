package com.rongcheng.hi

import android.app.Application
import com.rongcheng.hilog.HiLogConfig
import com.rongcheng.hilog.HiLogManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.init(object : HiLogConfig() {
            override fun getGlobalTag(): String {
                return "App "
            }

            override fun enable(): Boolean {
                return true
            }
        })
    }
}