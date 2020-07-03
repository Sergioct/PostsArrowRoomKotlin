package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.utils

import android.os.Build
import arrow.fx.IO
import arrow.integrations.retrofit.adapter.CallK
import arrow.integrations.retrofit.adapter.CallKind2CallAdapter
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class Effect2CallAdapterFactory : CallAdapter.Factory() {

    companion object {
        fun create() = Effect2CallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)

        if (returnType !is ParameterizedType) {
            val name = parseTypeName(returnType)
            throw IllegalArgumentException(
                "Return type must be parameterized as " +
                    "$name<Foo> or $name<out Foo>"
            )
        }

        val effectType = getParameterUpperBound(0, returnType)

        return when (rawType) {
            IO::class.java -> IOCallAdapter<Type>(effectType)
            CallK::class.java -> CallKind2CallAdapter<Type>(effectType)
            else -> null
        }
    }
}

private fun parseTypeName(type: Type) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        type.typeName.split(".").last()
    } else {
        ""
    }
