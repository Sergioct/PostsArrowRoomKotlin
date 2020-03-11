package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands

import arrow.fx.IO
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.models.CommentApi
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.models.PostApi
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Comment

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
interface FindCommentsFromPlaces {

	companion object {
		const val URL = "/places"
	}

	fun findCommentsFromPosts(): IO<Response>

	data class Response(
		val comments: List<CommentApi> = emptyList()
	)
}
