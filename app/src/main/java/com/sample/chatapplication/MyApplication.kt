package com.sample.chatapplication

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


@HiltAndroidApp
class MyApplication : MultiDexApplication(), LifecycleObserver {

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())


    companion object {
        var demoApp: MyApplication? = null
    }


    override fun onCreate() {
        super.onCreate()



        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        demoApp = this

    }




    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
//        isForeground = false
        Log.d("Awww", "App in background")
//        EventBus.getDefault().post(SocketDisconnectionEvent())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
//        isForeground = true
        Log.d("Yeeey", "App in foreground")

    }


}