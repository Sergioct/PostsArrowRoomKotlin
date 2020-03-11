package com.sergiocrespotoubes.postsarrowroomkotlin.data.repository

import androidx.lifecycle.LiveData
import arrow.fx.IO
import arrow.fx.extensions.fx
import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.dao.PostsDao
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.PostsService
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPostById
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.PostsRepository
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Comment
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post

/**
 * Created by Sergio Crespo Toubes on 09/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

class PostRepositoryImpl(
	private val postsDao: PostsDao,
	private val postsService: PostsService
): PostsRepository {

	override fun findPosts(): IO<LiveData<List<Post>>> = IO.fx {
		val findPostsResponse = !postsService.findPosts()
		postsDao.insert(findPostsResponse.posts)
		postsDao.findPosts()
	}

	override fun findPostById(): IO<Post> {
		TODO("Not yet implemented")
	}

	override fun findCommentsFromPosts(): IO<List<Comment>> {
		return postsService.findCommentsFromPosts().map { response ->
			response.comments.map { commentApi ->
				commentApi.toDomain()
			}
		}
	}

	override fun findAnswersFromAPost(): IO<List<Post>> {
		TODO("Not yet implemented")
	}
}
