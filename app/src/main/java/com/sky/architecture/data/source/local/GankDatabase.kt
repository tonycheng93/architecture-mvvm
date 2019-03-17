package com.sky.architecture.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sky.architecture.data.Article
import com.sky.architecture.utilities.DATABASE_NAME

/**
@author baocheng
@date 2019/3/9
 */
@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class GankDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {

        @Volatile
        private var instance: GankDatabase? = null

        fun getInstance(context: Context): GankDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): GankDatabase {
            return Room.databaseBuilder(context, GankDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .build()
        }
    }
}