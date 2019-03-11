package com.sky.architecture

import retrofit2.Response

/**
@author baocheng
@date 2019/3/9

 * Common class used by API responses.
 * @param <T> the type of the response object
</T>
 */
sealed class ApiResponse<T> {

    companion object {

        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val message = response.errorBody()?.string()
                val errorMessage = if (message.isNullOrEmpty()) {
                    response.message()
                } else {
                    message
                }

                ApiErrorResponse(errorMessage)
            }
        }
    }
}


data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>() {

}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()