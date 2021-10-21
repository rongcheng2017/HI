package com.rongcheng.hilog.timer

import com.rongcheng.hilog.HiLog
import com.rongcheng.hilog.HiLogConfig

open class HiLogDebugTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        t?.apply {
            HiLog.log(object : HiLogConfig() {
                override fun includeThread(): Boolean {
                    return true
                }

                override fun stackTraceDepth(): Int {
                    return 5
                }
            },priority, tag ?: "HiLogDebug", "$message ${t.localizedMessage}")
        }?:apply {
            HiLog.log(priority, tag ?: "HiLogDebug", "$message ")
        }

    }
}