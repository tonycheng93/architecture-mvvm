package com.sky.architecture.data

import androidx.annotation.NonNull

/**
@author baocheng
@date 2019/3/9
 * A generic class that holds a value with its loading status.
 * @param <T>
</T>
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> Success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> Error(message: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> Loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}