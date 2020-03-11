package com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

typealias UserId = Int
typealias PostId = Int

data class Post (
	val userId: UserId,
	val id : PostId,
	val title: String,
	val body: String
)
