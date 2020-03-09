package com.sergiocrespotoubes.postsarrowroomkotlin

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.context.postsModule
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.context.splashModule
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.managerModule
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class AppApplication : Application() {

	override fun onCreate() {
		super.onCreate()

		initKoin()
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
	}

	private fun initKoin() {
		startKoin {
			androidLogger()
			androidContext(this@AppApplication)
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
}