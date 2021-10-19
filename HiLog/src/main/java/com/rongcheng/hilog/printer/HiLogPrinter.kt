package com.rongcheng.hilog.printer

import androidx.annotation.NonNull
import com.rongcheng.hilog.HiLogConfig

interface HiLogPrinter {
    fun print(@NonNull config: HiLogConfig, level:Int, tag:String, @NonNull printString: String)
}