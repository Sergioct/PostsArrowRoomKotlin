package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sergiocrespotoubes.postsarrowroomkotlin.BuildConfig
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.BaseService
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.PostsServiceImpl
import com.sergiocrespotoubes.postsarrowroomkotlin.data.network.context.posts.PostsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
val networkModule = module {

	factory<PostsService> {
		PostsServiceImpl()
	}

	single {
		val gson: Gson = GsonBuilder().create()
		Retrofit.Builder()
			/*.addCallAdapterFactory(RxJava2CallAdapterFactory.create())*/
			.addConverterFactory(GsonConverterFactory.create(gson))
			.baseUrl(BaseService.BASE_URL)
			.client(get())
			.build()
	}

	fun getOkHttpClient(): OkHttpClient {

		fun createHttpInterceptor(builder: OkHttpClient.Builder) {
			if (BuildConfig.DEBUG) {
				val interceptor = HttpLoggingInterceptor()
				interceptor.level = HttpLoggingInterceptor.Level.BODY
				builder.addInterceptor(interceptor)
			}
		}

		val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
			override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) { /* Unnnused */ }

			override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) { /* Unnnused */  }

			override fun getAcceptedIssuers(): Array<X509Certificate> =
				arrayOf() // arrayOf(X509Certificate()[0])
		})

		val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
		trustManagerFactory.init(null as KeyStore?)
		val trustManagers = trustManagerFactory.trustManagers
		val trustManager = trustManagers[0] as X509TrustManager
		val sslContext = SSLContext.getInstance("SSL")
		sslContext.init(null, trustAllCerts, null)
		val sslSocketFactory = sslContext.socketFactory
		val hostnameVerifier = HostnameVerifier { hostname, _ -> hostname in BaseService.trustedHosts }

		val builder = OkHttpClient.Builder()
		createHttpInterceptor(builder)
		builder.sslSocketFactory(sslSocketFactory, trustManager)
		builder.hostnameVerifier(hostnameVerifier)

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