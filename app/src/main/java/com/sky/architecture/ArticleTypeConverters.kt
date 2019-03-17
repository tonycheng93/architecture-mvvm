package com.sky.architecture

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
@author baocheng
@date 2019/3/16
 */
object ArticleTypeConverters {

    @TypeConverter
    @JvmStatic
    fun stringToStringList(data: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun stringListToString(list: List<String>): String {
        return Gson().toJson(list)
    }
}