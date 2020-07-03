package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.utils.Effect2CallAdapterFactory
import com.sergiocrespotoubes.postsarrowroomkotlin.BuildConfig
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.BaseService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
val networkModule = module {
	single {
		val gson: Gson = GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ")
			.create()
		Retrofit.Builder()
			/*.addCallAdapterFactory(RxJava2CallAdapterFactory.create())*/
			.baseUrl(BaseService.BASE_URL)
			.client(get())
			.addConverterFactory(GsonConverterFactory.create(gson))
			.addCallAdapterFactory(Effect2CallAdapterFactory.create())
			.build()
	}

	fun getOkHttpClient(): OkHttpClient {

		val builder = OkHttpClient.Builder()

		if (BuildConfig.DEBUG) {
			val interceptor = HttpLoggingInterceptor()
			interceptor.level = HttpLoggingInterceptor.Level.BODY
			builder.addInterceptor(interceptor)
		}

		return builder
			.readTimeout(20, TimeUnit.SECONDS)
			.writeTimeout(20, TimeUnit.SECONDS)
			.connectTimeout(20, TimeUnit.SECONDS)
			.build()
	}

	single {
		getOkHttpClient()
	}

}
