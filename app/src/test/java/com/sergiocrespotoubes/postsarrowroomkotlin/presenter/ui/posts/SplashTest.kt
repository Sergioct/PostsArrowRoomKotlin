package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts

import androidx.lifecycle.Observer
import com.sergiocrespotoubes.postsarrowroomkotlin.BaseTest
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.splash.SplashViewModel
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.live_data.Event
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify

/**
 * Created by Sergio Crespo Toubes on 25/03/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class SplashTest : BaseTest() {

	init {
		val loadPlacesLiveDataMock = mockk<Observer<Event<Unit>>>()

		fun initViewModelObservers(vModel: SplashViewModel) {
			vModel.loadPlacesLiveData.observeForever(loadPlacesLiveDataMock)
		}

		every { loadPlacesLiveDataMock.onChanged(any()) } just Runs

		Given("splash view model") {

			val vModel = SplashViewModel()
			initViewModelObservers(vModel)

			When("init view model") {
				vModel.initViewModel()
				delay(2500L)

				Then("load marketplace view") {
					verify(exactly = 1) {
						loadPlacesLiveDataMock.onChanged(any())
					}
				}
			}
		}
	}

}
