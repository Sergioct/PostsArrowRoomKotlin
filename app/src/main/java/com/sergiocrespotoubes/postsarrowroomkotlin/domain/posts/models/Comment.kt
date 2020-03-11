package com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models

import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.Email
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.Name

/**
 * Created by Sergio Crespo Toubes on 11/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
data class Comment (
	val name: Name,
	val email: Email,
	val body: String
)
