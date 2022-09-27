package com.example.sensible

import android.app.Application
import com.example.sensible.di.dataModule
import com.example.sensible.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//TODO not sure if to use at all
class SensibleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SensibleApplication)
            modules(dataModule, networkModule)
        }
/*
        scope.launch {
            launch {
                val isForeground = callbackFlow {
                    val callback = object : DefaultLifecycleObserver {
                        override fun onStart(owner: LifecycleOwner) {
                            super.onStart(owner)
                            trySend(true)
                        }

                        override fun onStop(owner: LifecycleOwner) {
                            super.onStop(owner)
                            trySend(false)
                        }
                    }

                    withContext(Dispatchers.Main) {
                        ProcessLifecycleOwner.get().lifecycle.addObserver(callback)
                    }

                    awaitClose {
                        ProcessLifecycleOwner.get().lifecycle.removeObserver(callback)
                    }
                }
            }
        }

 */
    }
}