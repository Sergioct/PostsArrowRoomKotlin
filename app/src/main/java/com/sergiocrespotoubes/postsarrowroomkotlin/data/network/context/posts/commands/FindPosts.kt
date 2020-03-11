package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands

import arrow.fx.IO
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.models.PostApi

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
interface FindPosts {

	companion object {
		const val URL = "/posts"
	}

	fun findPosts(): IO<Response>

	data class Response(
		val posts: List<PostApi> = emptyList()
	)

}
