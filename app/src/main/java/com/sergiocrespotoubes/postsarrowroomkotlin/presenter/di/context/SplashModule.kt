package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.context

import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
val splashModule = module {
	viewModel { SplashViewModel() }
}