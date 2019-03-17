package com.sky.architecture

import android.os.Handler
import android.os.Message
import androidx.viewpager.widget.ViewPager
import java.lang.ref.WeakReference

/**
@author baocheng
@date 2019/3/16
 */
class AutoCarouselHandler constructor(
    private val pagerRef: WeakReference<ViewPager>,
    private val carouselMillis: Long = MSG_DELAY
) : Handler() {

    companion object {
        /**
         * 请求更新页面
         */
        const val MSG_UPDATE_IMAGE = 1
        /**
         * 请求暂停轮播
         */
        const val MSG_KEEP_SILENT = 2
        /**
         * 请求停止轮播
         */
        const val MSG_STOP_UPDATE = 3
        /**
         * 默认轮播间隔时间，用户触摸等待时间
         */
        const val MSG_DELAY: Long = 5000
    }

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        val viewPager = pagerRef.get() ?: return
        //避免多次刷新导致积累连续刷新
        if (hasMessages(MSG_STOP_UPDATE)) {
            removeMessages(MSG_UPDATE_IMAGE)
        }
        when (msg?.what) {
            MSG_UPDATE_IMAGE -> {
                viewPager.currentItem = viewPager.currentItem + 1
                sendEmptyMessageDelayed(MSG_STOP_UPDATE, carouselMillis)
            }
            MSG_KEEP_SILENT -> {
                if (hasMessages(MSG_UPDATE_IMAGE)) {
                    removeMessages(MSG_UPDATE_IMAGE)
                }
                sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, msg.obj as Long)
            }
            MSG_STOP_UPDATE -> {
                if (hasMessages(MSG_UPDATE_IMAGE)) {
                    removeMessages(MSG_UPDATE_IMAGE)
                }
                if (hasMessages(MSG_KEEP_SILENT)) {
                    removeMessages(MSG_KEEP_SILENT)
                }
            }
        }
    }
}