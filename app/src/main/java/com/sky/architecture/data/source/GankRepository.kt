package com.sky.architecture.data.source

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import com.sky.architecture.AppExecutors
import com.sky.architecture.data.Article
import com.sky.architecture.data.Resource
import com.sky.architecture.data.source.local.ArticleDao
import com.sky.architecture.data.source.remote.NetworkBoundResource
import com.sky.architecture.data.source.remote.api.ApiResponse
import com.sky.architecture.data.source.remote.api.GankService

/**
@author baocheng
@date 2019/3/15
 */
class GankRepository constructor(
    private val gankService: GankService,
    private val articleDao: ArticleDao,
    private val appExecutors: AppExecutors
) : GankDataSource {


    fun loadArticles(
        @NonNull type: String,
        size: Int,
        page: Int,
        @NonNull articleId: String
    ): LiveData<Resource<Article>> {
        return object : NetworkBoundResource<Article, Article>(appExecutors) {
            override fun saveCallResult(item: Article) {
                articleDao.insert(item)
            }

            override fun shouldFetch(data: Article?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Article> {
                return articleDao.findById(articleId)
            }

            override fun createCall(): LiveData<ApiResponse<Article>> {
                return gankService.fetchArticles(type, size, page)
            }

        }.asLiveData()
    }

    override fun saveArticle(article: Article) {

    }
}