package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di

import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.managers.PictureManager
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.managers.PictureManagerImpl
import org.koin.dsl.module

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
val managerModule = module {
	factory<PictureManager> { PictureManagerImpl() }
}