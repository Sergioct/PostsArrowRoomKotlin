package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.fx.IO
import arrow.fx.Timer
import arrow.fx.extensions.fx
import arrow.fx.extensions.io.concurrent.concurrent
import arrow.fx.typeclasses.milliseconds
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.live_data.Event

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
private const val SPLASH_DELAY_MILLIS = 2000L

class SplashViewModel: ViewModel() {

	val loadPlacesLiveData = MutableLiveData<Event<Unit>>()

	fun initViewModel() = IO.fx {
		Timer(IO.concurrent()).sleep(SPLASH_DELAY_MILLIS.milliseconds)
		laodMarketplace()
	}

	private fun laodMarketplace() {
		loadPlacesLiveData.value = Event(Unit)
	}

}