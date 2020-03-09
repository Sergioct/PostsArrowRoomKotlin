package com.sergiocrespotoubes.postsarrowroomkotlin.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.entities.Post

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
@Dao
interface PostsDao {

	@Query("SELECT * from posts")
	fun findPosts(): LiveData<List<Post>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(products: List<Post>)

	@Query("DELETE FROM posts")
	fun deleteAll()

}