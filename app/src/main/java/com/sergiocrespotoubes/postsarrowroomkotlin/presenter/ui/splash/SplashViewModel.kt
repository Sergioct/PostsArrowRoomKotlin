package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.fx.IO
import arrow.fx.extensions.fx
import arrow.fx.typeclasses.milliseconds
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.live_data.Event
import kotlinx.coroutines.Dispatchers

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
private const val SPLASH_DELAY_MILLIS = 3000L

class SplashViewModel: ViewModel() {

	val loadPlacesLiveData = MutableLiveData<Event<Unit>>()

	fun initViewModel() = IO.fx {
		sleep(SPLASH_DELAY_MILLIS.milliseconds).bind()
		continueOn(Dispatchers.Main)
		!loadPlaces()
	}

	private fun loadPlaces() = IO.fx {
		loadPlacesLiveData.value = Event(Unit)
	}

}
