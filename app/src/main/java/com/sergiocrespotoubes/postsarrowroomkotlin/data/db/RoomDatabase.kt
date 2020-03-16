package com.sergiocrespotoubes.postsarrowroomkotlin.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.dao.PostsDao
import com.sergiocrespotoubes.postsarrowroomkotlin.data.db.entities.PostDb

/**
 * Created by Sergio Crespo Toubes on 10/02/2020.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
@Database(
	entities = [
		PostDb::class
	],
	version = 1,
	exportSchema = false
)
//@TypeConverters(DateConverters::class)
abstract class PostsRoomDatabase : RoomDatabase() {

	abstract fun postsDao(): PostsDao

	companion object {
		// Singleton prevents multiple instances of database opening at the
		// same time.
		@Volatile
		private var INSTANCE: PostsRoomDatabase? = null

		fun getDatabase(context: Context): PostsRoomDatabase {
			val tempInstance = INSTANCE
			if (tempInstance != null) {
				return tempInstance
			}
			synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					PostsRoomDatabase::class.java,
					"app_db"
				).build()
				INSTANCE = instance
				return instance
			}
		}
	}
}
