package com.sergiocrespotoubes.postsarrowroomkotlin

import android.widget.GridLayout
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Observer
import com.sergiocrespotoubes.postsarrowroomkotlin.presenter.util.live_data.Event
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

open class BaseTest : BehaviorSpec() {

    // private val mainThreadSurrogate = newSingleThreadContext("Main thread")
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

    val showGenericErrorObsMock = mockk<Observer<Event<Unit>>>()

    override fun beforeSpec(spec: GridLayout.Spec) {
        super.beforeSpec(spec)
        Dispatchers.setMain(Dispatchers.Unconfined)
        // Dispatchers.setMain(mainThreadSurrogate)
        ArchTaskExecutor.getInstance().setDelegate(InstantTaskExecutor)
    }

    override fun afterSpec(spec: Spec) {
        super.afterSpec(spec)
        ArchTaskExecutor.getInstance().setDelegate(null)
        Dispatchers.resetMain()
        // mainThreadSurrogate.close()
    }
}

object InstantTaskExecutor : TaskExecutor() {
    override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
    }

    override fun isMainThread(): Boolean = true

    override fun postToMainThread(runnable: Runnable) {
        runnable.run()
    }
}
