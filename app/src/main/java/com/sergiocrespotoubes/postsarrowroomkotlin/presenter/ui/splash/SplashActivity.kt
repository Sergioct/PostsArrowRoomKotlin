package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.sergiocrespotoubes.postsarrowroomkotlin.R
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.managers.PictureManager
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts.PostsActivity
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.live_data.EventObserver
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.activities.start

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */

const val SPLASH_ANIMATION_TIME = 600L

class SplashActivity : AppCompatActivity() {

	private val vModel: SplashViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		loadViews()
		loadObservers()
		vModel.initViewModel().unsafeRunAsync {  }
	}

	private fun loadViews() {
		YoYo.with(Techniques.ZoomIn)
			.pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
			.duration(SPLASH_ANIMATION_TIME)
			.repeatMode(YoYo.INFINITE)
			.playOn(iv_logo)
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
