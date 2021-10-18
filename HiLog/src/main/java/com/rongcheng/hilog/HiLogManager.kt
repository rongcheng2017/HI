package com.rongcheng.hilog

import java.lang.IllegalArgumentException

class HiLogManager private constructor(public val config: HiLogConfig) {

    companion object {
        var sConfig: HiLogConfig? = null

        fun init(iConfig: HiLogConfig) {
            this.sConfig = iConfig
        }

        val instance: HiLogManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            sConfig?.let {
                HiLogManager(it)
            } ?: let {
                throw IllegalArgumentException("please invoke init() before getInstance()")
            }
        }

    }
}