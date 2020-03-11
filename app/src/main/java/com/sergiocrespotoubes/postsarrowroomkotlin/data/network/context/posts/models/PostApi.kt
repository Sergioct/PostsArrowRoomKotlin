package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.models

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
)
