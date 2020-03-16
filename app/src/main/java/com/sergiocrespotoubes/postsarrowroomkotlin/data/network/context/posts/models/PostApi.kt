package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.models

import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.entities.PostDb
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
data class PostApi (
	val userId: Int,
	val id : Int,
	val title: String,
	val body: String
){
	fun toDomain(): Post {
		return Post(
			userId = userId,
			id = id,
			title = title,
			body = body
		)
	}

	fun toDb(): PostDb {
		return PostDb(
			userId = userId,
			id = id,
			title = title,
			body = body
		)
	}
}
