package com.example.domain.entity.base

class ServerResponse<T> {

    companion object {
        const val STATUS_SUCCESS = 200
    }

    var data: T? = null
    var message: String? = null
    var status: Int = 0

    fun isSuccess() : Boolean {
        return status in 200..299
    }

}
