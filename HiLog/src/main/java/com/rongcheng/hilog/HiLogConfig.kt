package com.rongcheng.hilog

import com.rongcheng.hilog.formatter.HiStackTraceFormatter
import com.rongcheng.hilog.formatter.HiThreadFormatter
import com.rongcheng.hilog.printer.HiLogPrinter

abstract class HiLogConfig {

    companion object {
        const val MAX_LEN = 1024
        val HI_THREAD_FORMATTER = HiThreadFormatter()
        val HI_STACK_TRACE_FORMATTER = HiStackTraceFormatter()
    }


    open fun getGlobalTag(): String {
        return "HiLog"
    }

    open fun enable(): Boolean {
        return true
    }

    open fun injectJsonParser(): JsonParser? {
        return null
    }

    open fun includeThread(): Boolean {
        return false
    }

    open fun stackTraceDepth(): Int {
        return 5
    }

    open fun printers(): Array<HiLogPrinter>? {
        return null
    }

    interface JsonParser {
        fun toJson(src: Any):String
    }
}

class DefaultHiLogConfig : HiLogConfig()