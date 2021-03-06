package com.sergiocrespotoubes.postsarrowroomkotlin.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import arrow.fx.IO
import arrow.fx.extensions.fx
import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.dao.PostsDao
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.PostsService
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPostById
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.PostsRepository
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Comment
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.PostId

/**
 * Created by Sergio Crespo Toubes on 09/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

class PostsRepositoryImpl(
	private val postsDao: PostsDao,
	private val postsService: PostsService
): PostsRepository {

	override fun findPosts(): IO<List<Post>> = IO.fx {
		val findPostsResponse = !postsService.findPosts()
		val postsDb = findPostsResponse.posts
		val listPosts = postsDb.map { it.toDomain() }
		listPosts
	}

	override fun findPostsDB(): LiveData<List<Post>> {
		val postsDbLiveData = postsDao.findPosts()
		return Transformations.map(postsDbLiveData) { listPostDb ->
			listPostDb.map { it.toDomain() }
		}
	}

	override fun findPostById(postId: PostId): IO<Post> = IO.fx {
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
