package com.ms.app.app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.bdlbsc.doper.utils.thread.ThreadPoolUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {


    private  val TAG = "App"

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        Log.e(TAG, "onCreate: "+Thread.currentThread().name )
        Log.e(TAG, "onCreate: main :  "+ ThreadPoolUtils.isMainThread())


    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    companion object {
        var INSTANCE: App? = null
    }
}
