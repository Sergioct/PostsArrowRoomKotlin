package com.sergiocrespotoubes.postsarrowroomkotlin.data.network

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
class PostsServiceImpl() : PostsService, KoinComponent {

	val retrofit : Retrofit by inject()
	private val postsServiceApi: PostsServiceApi

	init {
		postsServiceApi = retrofit.create(PostsServiceApi::class.java)
	}

	private interface PostsServiceApi {

	}

	override suspend fun findPlaces(): FindPosts.Response {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override suspend fun findPlaceById(): FindPostById.Response {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override suspend fun findCommentsFromPlaces(): FindCommentsFromPlaces.Response {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override suspend fun findAnswers(): FindAnswers.Response {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}