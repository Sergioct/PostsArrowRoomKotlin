package com.sergiocrespotoubes.postsarrowroomkotlin

import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.Resource
import io.mockk.MockKVerificationScope
import io.mockk.clearMocks

/**
 * Created by Sergio Crespo Toubes on 05/04/2019.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
fun clearMockCalls(mock: Any) {
    clearMocks(
        mock,
        answers = false,
        recordedCalls = true,
        childMocks = false
    )
}

fun clearMockCalls(mockList: List<Any>) {
    mockList.forEach {
        clearMockCalls(it)
    }
}

fun <T> MockKVerificationScope.checkResourceLoading(): Resource<T, Throwable> {
    return match {
        it is Resource.Loading
    }
}

fun <T> MockKVerificationScope.checkResourceError(): Resource<T, Throwable> {
    return match {
        it is Resource.Error<*>
    }
}

fun <T, Z> MockKVerificationScope.checkResourceSuccess(value: Z): Resource<T, Throwable> {
    return match {
        it is Resource.Success<*> &&
        it.value == value
    }
}

fun <T> MockKVerificationScope.checkAnyResourceSuccess(): Resource<T, Throwable> {
    return match {
        it is Resource.Success<*>
    }
}

fun <T> MockKVerificationScope.checkAnyResource(): Resource<T, Throwable> {
    return any()
}
