package com.kevin.android_jetpack_compose_mvvm_demo.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()

    }
}