package com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts

import androidx.lifecycle.LiveData
import arrow.fx.IO
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Comment
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.PostId
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sergio Crespo Toubes on 11/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
interface PostsRepository {
	fun getPosts(): Flow<List<Post>>
	fun getPostsDB(): Flow<List<Post>>
	fun getPostById(postId: PostId): Flow<Post>
	fun getCommentsFromPosts(): Flow<List<Comment>>
	fun getAnswersFromAPost(): Flow<List<Post>>
}
