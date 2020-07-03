package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands

import arrow.fx.IO
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.PostId

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
interface FindPostById {

	companion object {
		const val URL = "/places"
	}

	fun findPostById(postId: PostId): IO<Post>
}
