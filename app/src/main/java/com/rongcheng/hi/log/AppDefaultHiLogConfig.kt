package com.rongcheng.hi.log

import com.google.gson.Gson
import com.rongcheng.hilog.HiLogConfig

object AppDefaultHiLogConfig : HiLogConfig(){
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
}