package com.android.githubrepositories

import android.app.Application
import com.android.githubrepositories.di.appModule
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule))
        }
    }
}