package com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts

import androidx.lifecycle.LiveData
import arrow.fx.IO
import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.entities.Post
import com.sergiocrespotoubes.postsarrowroomkotlin.data.repository.PostRepositoryImpl

/**
 * Created by Sergio Crespo Toubes on 09/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class FindPosts(val postRepositoryImpl: PostRepositoryImpl) {

	fun invoke() : IO<LiveData<List<Post>>>  {
		return postRepositoryImpl.findPosts()
	}

}