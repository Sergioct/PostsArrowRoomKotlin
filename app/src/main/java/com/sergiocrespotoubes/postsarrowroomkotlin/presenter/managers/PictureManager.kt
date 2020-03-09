package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.managers

import android.widget.ImageView
import coil.api.load

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
interface PictureManager {
	fun loadImage(
		imageView: ImageView,
		imageResource: Int,
		placeholderResource: Int,
		errorResource: Int)
}

class PictureManagerImpl : PictureManager {

	override fun loadImage(
		imageView: ImageView,
		imageResource: Int,
		placeholderResource: Int,
		errorResource: Int){

		imageView.load(imageResource){
			crossfade(true)
			placeholder(placeholderResource)
			error(errorResource)
		}
	}

}
