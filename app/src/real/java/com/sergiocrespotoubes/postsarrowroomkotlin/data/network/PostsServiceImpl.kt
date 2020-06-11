package com.sergiocrespotoubes.postsarrowroomkotlin.data.network

import arrow.fx.IO
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.PostsService
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindAnswers
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindCommentsFromPlaces
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPostById
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPosts
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class PostsServiceImpl : PostsService, KoinComponent {

	val retrofit : Retrofit by inject()
	//private val postsServiceApi: PostsServiceApi

	init {
		//postsServiceApi = retrofit.create(PostsServiceApi::class.java)
	}

	private interface PostsServiceApi {

	}

	override fun findPosts(): IO<FindPosts.Response> {
		TODO("Not yet implemented")
	}

	override fun findPostById(): IO<FindPostById.Response> {
		TODO("Not yet implemented")
	}

	override fun findCommentsFromPosts(): IO<FindCommentsFromPlaces.Response> {
		TODO("Not yet implemented")
	}

	override fun findAnswers(): IO<FindAnswers.Response> {
		TODO("Not yet implemented")
	}
}
