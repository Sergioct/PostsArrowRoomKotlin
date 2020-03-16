package com.sergiocrespotoubes.postsarrowroomkotlin.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
@Entity(tableName = "posts")
data class PostDb (
	@PrimaryKey val id : Int,
	val userId: Int,
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
}
