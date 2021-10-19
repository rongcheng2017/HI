package com.rongcheng.hilog.formatter

interface HiLogFormatter<T> {

    fun format(data:T):String?
}