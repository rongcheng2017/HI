package com.rongcheng.hi

import android.app.Application
import com.google.gson.Gson
import com.rongcheng.hilog.HiLogConfig
import com.rongcheng.hilog.HiLogManager
import com.rongcheng.hilog.printer.console.HiConsolePrinter

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
                return "App "
            }

            override fun enable(): Boolean {
                return true
            }
        }, HiConsolePrinter())
    }
}