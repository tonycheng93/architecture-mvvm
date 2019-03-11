package com.sky.architecture.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sky.architecture.data.Article

/**
@author baocheng
@date 2019/3/11
 */
@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Query("SELECT * FROM article WHERE id = :id")
    fun findById(id: String): LiveData<Article>
}