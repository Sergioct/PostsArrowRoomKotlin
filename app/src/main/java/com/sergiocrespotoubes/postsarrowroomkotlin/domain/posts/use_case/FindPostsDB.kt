package com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.use_case

import androidx.lifecycle.LiveData
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.PostsRepository
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post

/**
 * Created by Sergio Crespo Toubes on 09/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class FindPostsDB(private val postsRepository: PostsRepository) {
	fun invoke() : LiveData<List<Post>>  {
		return postsRepository.findPostsDB()
	}
}
