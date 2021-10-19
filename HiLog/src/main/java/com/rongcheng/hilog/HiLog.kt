package com.rongcheng.hilog

import androidx.annotation.NonNull
import com.rongcheng.hilog.formatter.HiStackTraceUtil
import com.rongcheng.hilog.printer.HiLogPrinter

/**
 * Tips:
 * 1. 打印堆栈信息
 * 2. File输出
 * 3. 模拟控制台
 */
class HiLog {

    companion object {
        private var HI_LOG_PACKAGE: String

        init {
            val className = HiLog::class.java.name
            HI_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1)
        }

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
            log(type, HiLogManager.instance.getConfig().getGlobalTag(), *contents)
        }

        fun log(@HiLogType.TYPE type: Int, @NonNull tag: String, vararg contents: Any) {
            log(HiLogManager.instance.getConfig(), type, tag, *contents)
        }

        fun log(
            config: HiLogConfig,
            @HiLogType.TYPE type: Int,
            tag: String,
            vararg contents: Any
        ) {
            if (!config.enable()) {
                return
            }
            val sb = StringBuilder()

            //include thread info ?
            if (config.includeThread()) {
                val threadInfo = HiLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread())
                sb.append(threadInfo).append("\n")
            }

            //include stacktrace info ?
            if (config.stackTraceDepth() > 0) {
                val stackTrace = HiLogConfig.HI_STACK_TRACE_FORMATTER.format(
                    HiStackTraceUtil.getCroppedRealStackTrace(
                        Throwable().stackTrace, HI_LOG_PACKAGE, config.stackTraceDepth()
                    )
                )
                sb.append(stackTrace).append("\n")
            }

            //parse content
            val body = parseBody(config, *contents)
            sb.append(body)

            // get printers
            val printers: List<HiLogPrinter> =
                config.printers()?.toList() ?: HiLogManager.instance.getPrinters()
            if (printers.isEmpty()) {
                throw IllegalArgumentException("HiLog Printers is Empty")
            }
            //print
            for (printer in printers) {
                printer.print(config, type, tag, sb.toString())
            }
        }

        private fun parseBody(config: HiLogConfig, vararg contents: Any): String {
            config.injectJsonParser()?.apply {
                return toJson(contents)
            }
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