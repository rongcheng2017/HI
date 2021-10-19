package com.rongcheng.hilog.printer.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rongcheng.hilog.HiLogConfig
import com.rongcheng.hilog.HiLogType
import com.rongcheng.hilog.R
import com.rongcheng.hilog.printer.HiLogMo
import com.rongcheng.hilog.printer.HiLogPrinter

/**
 * 将log显示在手机界面上
 */
class HiViewPrinter(activity: Activity) : HiLogPrinter {
    private var recyclerView: RecyclerView
    private var adapter: LogAdapter
    private var viewPrinterProvider: HiViewPrinterProvider

    init {
        val rootView = activity.findViewById<FrameLayout>(android.R.id.content)
        recyclerView = RecyclerView(activity)
        adapter = LogAdapter(LayoutInflater.from(recyclerView.context))
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        viewPrinterProvider = HiViewPrinterProvider(rootView, recyclerView)

    }

    fun getViewProvider(): HiViewPrinterProvider {
        return viewPrinterProvider
    }

    override fun print(config: HiLogConfig, level: Int, tag: String, printString: String) {
        adapter.addItem(HiLogMo(System.currentTimeMillis(), level, tag, printString))
        recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }


    private class LogAdapter(private val inflater: LayoutInflater) :
        RecyclerView.Adapter<LogViewHolder>() {
        private val logs: MutableList<HiLogMo> = arrayListOf()

        fun addItem(hiLogMo: HiLogMo) {
            logs.add(hiLogMo)
            notifyItemInserted(logs.size - 1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
            val itemView = inflater.inflate(R.layout.hilog_item, parent, false)
            return LogViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
            val logItem = logs[position]
            val color = getHighlightColor(logItem.level)
            holder.tagView.setTextColor(color)
            holder.messageView.setTextColor(color)

            holder.tagView.text = logItem.getFlattened()
            holder.messageView.text = logItem.log
        }

        override fun getItemCount(): Int {
            return logs.size
        }

        private fun getHighlightColor(logLevel: Int): Int {
            val highlight = when (logLevel) {
                HiLogType.V -> 0xffbbbbbb
                HiLogType.D -> 0xffffffff
                HiLogType.I -> 0xff6a8759
                HiLogType.W -> 0xffbbb529
                HiLogType.E -> 0xffff6b68
                else -> 0xffffff00
            }
            return highlight.toInt()

        }


    }

    private class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tagView: TextView = itemView.findViewById(R.id.tag)
        var messageView: TextView = itemView.findViewById(R.id.message)

    }
}