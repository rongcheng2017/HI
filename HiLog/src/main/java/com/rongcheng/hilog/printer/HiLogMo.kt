package com.rongcheng.hilog.printer

import java.text.SimpleDateFormat
import java.util.*

class HiLogMo(
    private val timeMillis: Long,
     val level: Int,
     val tag: String,
     val log: String
) {

    fun flattenedLog(): String {
        return getFlattened() + "\n" + log
    }

    fun getFlattened(): String {
        return format(timeMillis) + "|" + level + "|" + tag + "|:"
    }

    fun format(timeMills: Long): String {
        return sdf.format(timeMillis)
    }

    companion object {
        private val sdf: SimpleDateFormat = SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA)

    }
}