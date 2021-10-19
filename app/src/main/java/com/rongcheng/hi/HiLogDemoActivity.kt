package com.rongcheng.hi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rongcheng.hilog.HiLog
import com.rongcheng.hilog.HiLogConfig
import com.rongcheng.hilog.HiLogManager
import com.rongcheng.hilog.HiLogType
import com.rongcheng.hilog.printer.view.HiViewPrinter

class HiLogDemoActivity : AppCompatActivity() {
     var viewPrinter:HiViewPrinter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)
        viewPrinter= HiViewPrinter(this)
        findViewById<View>(R.id.btn_log).setOnClickListener {
            printLog()
        }
        viewPrinter?.getViewProvider()?.showFloatingView()
    }

    private fun printLog() {
        viewPrinter?.apply {
            HiLogManager.instance.addPrinter(this)
        }
        HiLog.log(object : HiLogConfig() {
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, HiLogType.E, "----", "5566")
        HiLog.a("9900", "aaa", "bbb")
    }

}