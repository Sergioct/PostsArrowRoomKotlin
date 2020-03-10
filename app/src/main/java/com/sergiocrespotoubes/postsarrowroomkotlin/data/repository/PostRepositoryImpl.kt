package com.sergiocrespotoubes.postsarrowroomkotlin.data.repository

import androidx.lifecycle.LiveData
import arrow.fx.IO
import arrow.fx.extensions.fx
import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.dao.PostsDao
import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.entities.Post
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.PostsService
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindAnswers
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindCommentsFromPlaces
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPostById

/**
 * Created by Sergio Crespo Toubes on 09/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

interface PostRepository {
	fun findPosts(): IO<LiveData<List<Post>>>
	fun findPostById(): IO<FindPostById.Response>
	fun findCommentsFromPlaces(): IO<FindCommentsFromPlaces.Response>
	fun findAnswers(): IO<FindAnswers.Response>
}

class PostRepositoryImpl(
	private val postsDao: PostsDao,
	private val postsService: PostsService
): PostRepository {

	override fun findPosts(): IO<LiveData<List<Post>>> = IO.fx {
		val findPostsResponse = !postsService.findPosts()
		postsDao.insert(findPostsResponse.posts)
		postsDao.findPosts()
	}

	override fun findPostById(): IO<FindPostById.Response> {
		return postsService.findPostById()
	}

	override fun findCommentsFromPlaces(): IO<FindCommentsFromPlaces.Response> {
		return postsService.findCommentsFromPlaces()
	}

	override fun findAnswers(): IO<FindAnswers.Response> {
		return postsService.findAnswers()
	}
}
