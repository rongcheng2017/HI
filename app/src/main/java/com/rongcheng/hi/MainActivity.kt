package com.rongcheng.hi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tencent.mars.xlog.Log

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_hilog -> startActivity(Intent(this, HiLogDemoActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.appenderClose()
    }
}