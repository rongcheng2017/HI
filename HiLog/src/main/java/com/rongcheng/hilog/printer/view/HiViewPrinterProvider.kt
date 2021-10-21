package com.rongcheng.hilog.printer.view

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rongcheng.hilog.util.HiDisplayUtil

class HiViewPrinterProvider {

     val rootView: FrameLayout
    var floatingView: View? = null
    var isOpen: Boolean = false
    var logView: FrameLayout? = null
    val recyclerView: RecyclerView
    val TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW"
    val TAG_LOG_VIEW = "TAG_LOG_VIEW"

    constructor(rootView: FrameLayout, recyclerView: RecyclerView) {
        this.rootView = rootView
        this.recyclerView = recyclerView
    }

    fun showFloatingView() {
        if (rootView.findViewWithTag<FrameLayout>(TAG_FLOATING_VIEW) != null) {
            return
        }
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.BOTTOM or Gravity.END
        val floatingView = genFloatingView()
        floatingView.tag = TAG_FLOATING_VIEW
        floatingView.setBackgroundColor(Color.BLACK)
        floatingView.alpha = 0.8f
        params.bottomMargin = HiDisplayUtil.dp2px(100f, recyclerView.resources)
        rootView.addView(floatingView, params)
    }

    private fun genFloatingView(): View {
        if (floatingView != null)
            return floatingView!!
        val textView = TextView(rootView.context)
        textView.setOnClickListener {
            if (!isOpen) {
                showLogView()
            }
        }
        textView.text = "HiLog"
        floatingView = textView
        return floatingView!!
    }

    private fun showLogView() {
        if (rootView.findViewWithTag<FrameLayout>(TAG_LOG_VIEW) != null)
            return
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            HiDisplayUtil.dp2px(160f, rootView.resources)
        )
        params.gravity = Gravity.BOTTOM
        val logView = genLogView()
        logView.tag = TAG_LOG_VIEW
        rootView.addView(logView, params)
        isOpen=true

    }

    private fun genLogView(): View {
        if (logView != null)
            return logView!!
        val logView = FrameLayout(rootView.context)
        logView.setBackgroundColor(Color.BLACK)
        logView.addView(recyclerView)

        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.END
        val closeView = TextView(rootView.context)
        closeView.setOnClickListener { closeLogView() }
        closeView.text = "Close"
        logView.addView(closeView, params)
        this.logView = logView
        return this.logView!!
    }

    private fun closeLogView() {
        isOpen = false
        rootView.removeView(genLogView())
    }

    public fun closeFloatingView() {
        rootView.removeView(genFloatingView())
    }


}