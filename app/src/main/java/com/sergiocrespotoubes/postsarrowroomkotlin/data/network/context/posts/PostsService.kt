package com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts

import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindAnswers
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindCommentsFromPlaces
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPostById
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.commands.FindPosts

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
interface PostsService:
	FindPosts,
	FindPostById,
	FindCommentsFromPlaces,
	FindAnswers