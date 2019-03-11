package com.sky.architecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sky.architecture.data.Article

/**
@author baocheng
@date 2019/3/9
 */
@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class GankDb : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}