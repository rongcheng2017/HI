package com.rongcheng.hilog.formatter

class HiStackTraceFormatter : HiLogFormatter<Array<StackTraceElement?>> {
    override fun format(data: Array<StackTraceElement?>): String? {
        val sb = StringBuilder()
        when {
            data.isEmpty() -> {
                return null
            }
            data.size == 1 -> {
                return "\t- ${data[0]}"
            }
            else -> {
                data.forEachIndexed { index, stackTrace ->
                    if (index == 0) {
                        sb.append("stackTrace: \n")
                    }
                    if (index != data.size - 1) {
                        sb.append("\t├")
                        sb.append(stackTrace.toString())
                        sb.append("\n")
                    } else {
                        sb.append("\t⎣")
                        sb.append(stackTrace.toString())
                    }
                }
            }
        }
        return sb.toString()
    }
}