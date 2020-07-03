package com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts

import androidx.lifecycle.LiveData
import arrow.fx.IO
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Comment
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.PostId

/**
 * Created by Sergio Crespo Toubes on 11/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
interface PostsRepository {
	fun findPosts(): IO<List<Post>>
	fun findPostsDB(): LiveData<List<Post>>
	fun findPostById(postId: PostId): IO<Post>
	fun findCommentsFromPosts(): IO<List<Comment>>
	fun findAnswersFromAPost(): IO<List<Post>>
}
