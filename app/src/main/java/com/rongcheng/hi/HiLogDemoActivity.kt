package com.rongcheng.hi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rongcheng.hilog.HiLog

class HiLogDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)
        findViewById<View>(R.id.btn_log).setOnClickListener {
            printLog()
        }
    }

    private fun printLog() {
        HiLog.a("9900","aaa","bbb")
    }

}