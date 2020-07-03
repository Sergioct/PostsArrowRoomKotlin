package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.ui.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.sergiocrespotoubes.postsarrowroomkotlin.R
import kotlinx.android.synthetic.main.include_appbar.*

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
class PostsActivity : AppCompatActivity() {

	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_posts)
		loadAppbar()
	}

	private fun loadAppbar() {
		val navController = findNavController(R.id.nav_host_fragment)
		appBarConfiguration = AppBarConfiguration(navController.graph)
		setSupportActionBar(toolbar)

		val abc = AppBarConfiguration.Builder().build()
		NavigationUI.setupActionBarWithNavController(this, navController, abc)
		AppBarConfiguration.Builder().setFallbackOnNavigateUpListener {
			super.onSupportNavigateUp()
		}.build()
	}

}
