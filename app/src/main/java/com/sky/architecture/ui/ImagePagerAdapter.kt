package com.sky.architecture.ui

import android.os.Handler
import android.os.Message
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.sky.architecture.AutoCarouselHandler
import java.lang.ref.WeakReference
import java.util.*

/**
@author baocheng
@date 2019/3/16
 */
class ImagePagerAdapter constructor(
    private val handler: Handler,
    private val images: List<String>,
    viewPager: ViewPager,
    private val carouselMillis: Long
) : PagerAdapter(), Carouselable {

    private val viewCachePool = LinkedList<View>()
    private var viewPagerRef: WeakReference<ViewPager> = WeakReference(viewPager)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View
        var viewHolder: ViewHolder
        if (viewCachePool.size > 0) {
            view = viewCachePool.poll()
            viewHolder = view.tag as ViewHolder
        } else {
            view = ImageView(container.context)
            viewHolder = ViewHolder()
            viewHolder.imageView = view
            view.tag = viewHolder
        }
        bindData(images[position], viewHolder.imageView)
        container.addView(view)
        return view
    }

    private fun bindData(@NonNull imageUrl: String, @NonNull imageView: ImageView) {
        TODO("load image")
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        viewCachePool.offer(`object`)
    }

    override fun startCarousel() {
        val position = count / 2 - (count % images.size)
        viewPagerRef.get()?.currentItem = position
        handler.sendEmptyMessageDelayed(AutoCarouselHandler.MSG_UPDATE_IMAGE, carouselMillis)
    }

    override fun stopCarousel() {
        handler.sendEmptyMessage(AutoCarouselHandler.MSG_STOP_UPDATE)
    }

    override fun pauseCarousel() {
        pauseCarousel(carouselMillis)
    }

    override fun pauseCarousel(millis: Long) {
        val message = Message.obtain()
        message.what = AutoCarouselHandler.MSG_KEEP_SILENT
        message.obj = millis
        handler.sendMessage(message)
    }

    class ViewHolder {
        lateinit var imageView: ImageView
    }
}