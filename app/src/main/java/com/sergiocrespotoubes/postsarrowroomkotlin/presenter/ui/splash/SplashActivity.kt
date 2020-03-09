package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sergiocrespotoubes.postsarrowroomkotlin.R
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.managers.PictureManager
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts.PostsActivity
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.live_data.EventObserver
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.activities.start

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class SplashActivity : AppCompatActivity() {

	private val vModel: SplashViewModel by viewModel()

	private val pictureManager : PictureManager by inject()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		loadObservers()
		vModel.initViewModel()
	}

	private fun loadObservers() {
		vModel.loadPlacesLiveData.observe(this, EventObserver {
			loadMarketplace()
		})
	}

	private fun loadMarketplace() {
		start<PostsActivity>()
		finish()
	}

}