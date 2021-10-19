package com.rongcheng.hilog.formatter

class HiStackTraceUtil {
    companion object {

         fun getCroppedRealStackTrace(
            stackTrace: Array<StackTraceElement>,
            ignorePackage: String?, maxDepth: Int
        ): Array<StackTraceElement?> {
            return cropStackTrace(getRealStackTrace(stackTrace, ignorePackage), maxDepth)
        }

        /**
         * 获取真实有用的堆栈信息，Log包内的不需要打印
         */
        private fun getRealStackTrace(
            stackTrace: Array<StackTraceElement>,
            ignorePackage: String?
        ): Array<StackTraceElement?> {
            var ignoreDepth = 0
            val allDepth = stackTrace.size
            var className: String
            for (i in allDepth - 1 downTo 0) {
                className = stackTrace[i].className
                if (ignorePackage != null && className.startsWith(ignorePackage)) {
                    ignoreDepth = i + 1
                    break
                }
            }
            val realDepth = allDepth - ignoreDepth
            val realStack = arrayOfNulls<StackTraceElement>(realDepth)
            System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth)
            return realStack

        }

        private fun cropStackTrace(
            callStack: Array<StackTraceElement?>,
            maxDepth: Int
        ): Array<StackTraceElement?> {
            var realDepth = callStack.size
            if (maxDepth > 0) {
                realDepth = maxDepth.coerceAtMost(realDepth)
            }
            val realStack = arrayOfNulls<StackTraceElement>(realDepth)
            System.arraycopy(callStack, 0, realStack, 0, realDepth)
            return realStack
        }
    }
}