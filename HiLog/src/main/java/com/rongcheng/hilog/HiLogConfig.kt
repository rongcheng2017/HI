package com.rongcheng.hilog

abstract class HiLogConfig {

    open fun getGlobalTag(): String {
        return "HiLog"
    }

    open fun enable(): Boolean {
        return true
    }
}

class DefaultHiLogConfig : HiLogConfig()