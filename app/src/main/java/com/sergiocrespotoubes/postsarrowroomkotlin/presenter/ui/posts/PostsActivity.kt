package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sergiocrespotoubes.postsarrowroomkotlin.R
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class PostsActivity : AppCompatActivity() {

	private val vModel: SplashViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_posts)

		vModel.initViewModel()
	}

}