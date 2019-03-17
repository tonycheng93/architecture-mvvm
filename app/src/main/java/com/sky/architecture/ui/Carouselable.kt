package com.sky.architecture.ui

/**
@author baocheng
@date 2019/3/17
 */
interface Carouselable {

    /**
     * 开始轮播
     */
    fun startCarousel()

    /**
     * 停止轮播
     */
    fun stopCarousel()

    /**
     * 暂停轮播，自动恢复轮播，默认暂停时间为轮播的切换时间
     */
    fun pauseCarousel()

    /**
     * 暂停轮播，自主设置暂停轮播时间
     * @param millis
     */
    fun pauseCarousel(millis: Long)
}