package com.sky.architecture.data

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sky.architecture.ArticleTypeConverters

/**
@author baocheng
@date 2019/3/9
 */
@Entity(tableName = "article", primaryKeys = ["id"])
@TypeConverters(ArticleTypeConverters::class)
data class Article(
    @field:SerializedName("_id")
    val id: String,
    @field:SerializedName("createdAt")
    val createdAt: String,
    @field:SerializedName("desc")
    val desc: String,
    @field:SerializedName("publishedAt")
    val publishedAt: String,
    @field:SerializedName("source")
    val source: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("used")
    val used: Boolean,
    @field:SerializedName("who")
    val author: String,
    @field:SerializedName("images")
    val images: List<String>
)
