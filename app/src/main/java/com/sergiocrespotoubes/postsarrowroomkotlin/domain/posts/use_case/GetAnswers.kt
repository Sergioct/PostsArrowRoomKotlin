package com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.use_case

import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.PostsRepository
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sergio Crespo Toubes on 09/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class GetAnswers(private val postsRepository: PostsRepository) {
	suspend fun invoke(): Flow<List<Post>> {
		return postsRepository.getAnswersFromAPost()
	}
}
