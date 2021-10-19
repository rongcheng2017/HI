package com.rongcheng.hilog.printer

import android.util.Log
import com.rongcheng.hilog.HiLogConfig

class HiConsolePrinter : HiLogPrinter {
    override fun print(config: HiLogConfig, level: Int, tag: String, printString: String) {
        val len = printString.length
        val everyDepthLength = HiLogConfig.MAX_LEN
        val countOfStub = len / everyDepthLength
//        if (len % everyDepthLength != 0) {
//            countOfStub++
//        }
        if (countOfStub > 0) {
            var index = 0
            for (i in 0 until countOfStub) {
                Log.println(level, tag, printString.substring(index, index + everyDepthLength))
                index += everyDepthLength
            }
            if (index != len) {
                Log.println(level, tag, printString.substring(index, len))
            }
        }else{
            Log.println(level,tag,printString)
        }
    }
}