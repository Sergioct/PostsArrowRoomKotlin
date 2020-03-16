package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts.list

import com.sergiocrespotoubes.postsarrowroomkotlin.R
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.models.Post
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_post.*

/**
 * Created by Sergio Crespo Toubes on 01/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

class PostItem(
	val post: Post,
	val onItemClick: (Post) -> Unit
) : Item() {

	override fun getLayout() = R.layout.item_post

	override fun bind(holder: GroupieViewHolder, position: Int) {
		holder.tv_name.text = post.title
	}

}
