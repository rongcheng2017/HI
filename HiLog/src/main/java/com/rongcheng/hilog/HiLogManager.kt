package com.rongcheng.hilog

import com.rongcheng.hilog.printer.HiLogPrinter

//todo 保证单例的线程安全
class HiLogManager private constructor(
    private val config: HiLogConfig,
    vararg printers: HiLogPrinter
) {
    private val mPrinters: MutableList<HiLogPrinter> = arrayListOf()

    companion object {
        lateinit var instance: HiLogManager
        fun init(config: HiLogConfig, vararg printers: HiLogPrinter) {
            instance = HiLogManager(config, *printers)
        }

//        val instance: HiLogManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            sConfig?.let {
//                HiLogManager(it)
//            } ?: let {
//                throw IllegalArgumentException("please invoke init() before getInstance()")
//            }
//        }

    }

    init {
        mPrinters.addAll(printers)
    }

    fun getConfig(): HiLogConfig {
        return config
    }

    fun getPrinters(): List<HiLogPrinter> {
        return mPrinters
    }

    fun addPrinter(printer: HiLogPrinter) {
        mPrinters.add(printer)
    }

    fun removePrinter(printer: HiLogPrinter) {
        if (mPrinters.isNotEmpty()) {
            mPrinters.remove(printer)
        }
    }


}