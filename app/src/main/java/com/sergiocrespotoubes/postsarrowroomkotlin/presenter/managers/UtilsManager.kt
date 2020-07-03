package com.sergiocrespotoubes.postsarrowroomkotlin.presenter.managers

import android.content.Context
import android.content.pm.ApplicationInfo

/**
 * Created by Sergio Crespo Toubes on 23/01/2019.
 *     SergioCrespoToubes@gmail.com
 *     www.SergioCrespoToubes.com
 */
interface UtilsManager {
    fun isDebuggable(): Boolean
    fun isRelease(): Boolean
}

class UtilsManagerImpl(private val context: Context) : UtilsManager {

    override fun isDebuggable(): Boolean {
        return context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }

    override fun isRelease(): Boolean {
        return !isDebuggable()
    }
}
