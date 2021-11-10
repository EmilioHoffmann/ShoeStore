package com.udacity.shoestore

import android.app.Application
import com.udacity.shoestore.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ShoeStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ShoeStoreApplication)
            modules(appModule)
        }
    }
}
