package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util

data class Resource<out T>(
    val status: Status,
    val data: Any?,
    val message: String = ""
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(data: Throwable? = null, message: String = ""): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, "")
        }
    }
}
