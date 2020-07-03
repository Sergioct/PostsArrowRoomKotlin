package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.utils

import arrow.core.Either
import arrow.fx.IO
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class IOCallAdapter<R>(private val type: Type) : CallAdapter<R, IO<R>> {
    override fun adapt(call: Call<R>): IO<R> =
        IO.async { function: (Either<Throwable, R>) -> Unit ->
            call.enqueue(ResponseCallback(function))
        }

    override fun responseType(): Type = type
}
