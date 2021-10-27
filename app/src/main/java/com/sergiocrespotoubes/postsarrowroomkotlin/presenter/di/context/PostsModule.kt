package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.context

import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.PostsRoomDatabase.Companion.getDatabase
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.PostsServiceImpl
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.PostsService
import com.sergiocrespotoubes.postsarrowroomkotlin.data.repository.PostsRepositoryImpl
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.PostsRepository
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.use_case.GetAnswers
import com.sergiocrespotoubes.postsarrowroomkotlin.domain.posts.use_case.FindPosts
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts.list.PostsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

val postsModule = module {
	viewModel { PostsListViewModel(get()) }

	factory { getDatabase(get()).postsDao() }
	factory<PostsRepository> { PostsRepositoryImpl(get(), get()) }
	factory<PostsService> { PostsServiceImpl() }
	factory { FindPosts(get()) }
	factory { GetAnswers(get()) }
}
