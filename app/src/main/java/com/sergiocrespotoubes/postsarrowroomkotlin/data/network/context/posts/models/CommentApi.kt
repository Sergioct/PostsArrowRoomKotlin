package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.models

import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Comment
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.CommentId
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.PostId
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.Email
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.Name

/**
 * Created by Sergio Crespo Toubes on 11/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

data class CommentApi(
	val postId: PostId,
	val commentId: CommentId,
	val name: Name,
	val email: Email,
	val body: String
){
	fun toDomain(): Comment{
		return Comment(
			name = name,
			email = email,
			body = body
		)
	}
}
