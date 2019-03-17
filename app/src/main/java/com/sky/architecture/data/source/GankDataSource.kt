package com.sky.architecture.data.source

import androidx.annotation.NonNull
import com.sky.architecture.data.Article

/**
@author baocheng
@date 2019/3/9
 */
interface GankDataSource {

    fun saveArticle(@NonNull article: Article)
}