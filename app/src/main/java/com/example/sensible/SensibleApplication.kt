package com.example.sensible

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.sensible.data.resetAppSettings
import com.example.sensible.di.koinModule
import com.example.sensible.util.isFirstRun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//TODO not sure if to use at all
class SensibleApplication : Application() {
    private val preferences: DataStore<Preferences> by inject()
    private val scope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SensibleApplication)
            modules(koinModule)
        }

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
            if (isFirstRun()) {
                preferences.resetAppSettings()
            }
        }
    }
}