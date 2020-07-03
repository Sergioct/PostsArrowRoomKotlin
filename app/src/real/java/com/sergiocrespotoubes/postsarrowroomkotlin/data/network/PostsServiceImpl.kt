package com.sergiocrespotoubes.postsarrowroomkotlin.data.network

import arrow.fx.IO
import arrow.fx.extensions.fx
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.PostsService
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindAnswers
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindCommentsFromPlaces
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPostById
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPosts
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.PostId
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.http.GET

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class PostsServiceImpl : PostsService, KoinComponent {

	private val retrofit : Retrofit by inject()
	private val postsServiceApi: PostsServiceApi

	init {
		postsServiceApi = retrofit.create(PostsServiceApi::class.java)
	}

	private interface PostsServiceApi {
		@GET(FindPosts.URL)
		fun findPosts(): IO<FindPosts.Response>

		@GET(FindPostById.URL)
		fun findPostById(postId: PostId): IO<Post>

		@GET(FindCommentsFromPlaces.URL)
		fun findCommentsFromPosts(): IO<FindCommentsFromPlaces.Response>

		@GET(FindAnswers.URL)
		fun findAnswers(): IO<FindAnswers.Response>
	}

	override fun findPosts(): IO<FindPosts.Response> = IO.fx {
		!postsServiceApi.findPosts()
	}

	override fun findPostById(postId: PostId): IO<Post> = IO.fx {
		!postsServiceApi.findPostById(postId)
	}

	override fun findCommentsFromPosts(): IO<FindCommentsFromPlaces.Response> = IO.fx {
		!postsServiceApi.findCommentsFromPosts()
	}

	override fun findAnswers(): IO<FindAnswers.Response> = IO.fx {
		!postsServiceApi.findAnswers()
	}
}
