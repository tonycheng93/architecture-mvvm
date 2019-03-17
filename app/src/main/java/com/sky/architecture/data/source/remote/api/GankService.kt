package com.sky.architecture.data.source.remote.api

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import com.sky.architecture.data.Article
import retrofit2.http.GET

/**
@author baocheng
@date 2019/3/15
 */
interface GankService {

    /**
     * fetch today's article
     */
    @GET("today")
    fun fetchTodayArticle(): LiveData<ApiResponse<Article>>

    /**
     * get articles by type
     * @param type the article type,such as Android,iOS,etc.
     * @param size the size of pear page.
     * @param page the page of pear fetch.
     */
    @GET("{type}/{size}/{page}")
    fun fetchArticles(@NonNull type: String, size: Int, page: Int): LiveData<ApiResponse<Article>>
}