package com.rongcheng.hilog

import android.util.Log
import androidx.annotation.NonNull
import java.util.*

/**
 * Tips:
 * 1. 打印堆栈信息
 * 2. File输出
 * 3. 模拟控制台
 */
class HiLog {

    companion object {
        fun v(vararg contents: Any) {
            log(HiLogType.V, contents)
        }

        fun vt(tag: String, vararg contents: Any) {
            log(HiLogType.V, tag, contents)
        }

        fun d(vararg contents: Any) {
            log(HiLogType.D, contents)
        }

        fun vdt(tag: String, vararg contents: Any) {
            log(HiLogType.D, tag, contents)
        }

        fun i(vararg contents: Any) {
            log(HiLogType.I, contents)
        }

        fun it(tag: String, vararg contents: Any) {
            log(HiLogType.I, tag, contents)
        }

        fun w(vararg contents: Any) {
            log(HiLogType.W, contents)
        }

        fun wt(tag: String, vararg contents: Any) {
            log(HiLogType.W, tag, contents)
        }

        fun e(vararg contents: Any) {
            log(HiLogType.E, contents)
        }

        fun et(tag: String, vararg contents: Any) {
            log(HiLogType.E, tag, contents)
        }

        fun a(vararg contents: Any) {
            log(HiLogType.A, *contents)
        }

        fun at(tag: String, vararg contents: Any) {
            log(HiLogType.A, tag, *contents)
        }

        fun log(@HiLogType.TYPE type: Int, vararg contents: Any) {
            log(type, HiLogManager.sConfig?.getGlobalTag() ?: "HiLog", *contents)
        }

        fun log(@HiLogType.TYPE type: Int, @NonNull tag: String, vararg contents: Any) {
            log(HiLogManager.sConfig ?: DefaultHiLogConfig(), type, tag, *contents)
        }

        fun log(
            @NonNull config: HiLogConfig,
            @HiLogType.TYPE type: Int,
            @NonNull tag: String,
            vararg contents: Any
        ) {
            if (!config.enable()) {
                return
            }
            val sb = StringBuilder()
            val body = parseBody(*contents)
            sb.append(body)
            Log.println(type, tag, sb.toString())
        }

        private fun parseBody(@NonNull vararg contents: Any): String {
            val sb = StringBuilder()
            for (content in contents) {
                sb.append(content.toString()).append(";")
            }
            if (sb.isNotEmpty()) {
                sb.deleteCharAt(sb.length - 1)
            }
            return sb.toString()
        }
    }
}