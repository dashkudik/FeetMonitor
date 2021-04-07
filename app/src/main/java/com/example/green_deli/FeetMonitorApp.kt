package com.example.green_deli

import android.app.Application
import com.example.green_deli.domain.repository.AppRepository
import com.example.green_deli.gateway.AppRepositoryImpl
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : Application() {
    companion object {
        var appRepository: AppRepository? = null
    }

    override fun onCreate() {
        super.onCreate()
        appRepository = AppRepositoryImpl(this)
    }
}