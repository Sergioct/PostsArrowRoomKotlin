package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util

sealed class Resource<out T, out E> {
	class Success<out T> internal constructor(val value: T) : Resource<T, Nothing>()

	class Error<out E> internal constructor(val value: E) : Resource<Nothing, E>()

	object Loading : Resource<Nothing, Nothing>()

	companion object {
		fun <T> success(value: T) = Success(value)

		fun <E> error(value: E) = Error(value)

		fun loading() = Loading
	}
}
