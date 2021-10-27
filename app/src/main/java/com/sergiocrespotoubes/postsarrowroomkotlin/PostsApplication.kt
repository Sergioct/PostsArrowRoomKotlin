package com.sergiocrespotoubes.postsarrowroomkotlin

import android.app.Application
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.context.postsModule
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.context.splashModule
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.managerModule
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.networkModule
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.managers.UtilsManager
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class PostsApplication : Application() {

	override fun onCreate() {
		super.onCreate()

		initKoin()
		initTimber()
	}

	private fun initKoin() {
		startKoin {
			androidLogger()
			androidContext(this@PostsApplication)
			// androidFileProperties()
			modules(
				listOf(
					managerModule,
					networkModule,
					splashModule,
					postsModule
				)
			)
		}
	}

	private fun initTimber() {
		val utilsManager: UtilsManager by inject()
		if (utilsManager.isDebuggable()) {
			Timber.plant(Timber.DebugTree())
		}
	}
}
