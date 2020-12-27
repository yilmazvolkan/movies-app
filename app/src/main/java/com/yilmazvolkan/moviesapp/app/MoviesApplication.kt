package com.yilmazvolkan.moviesapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApplication : Application() {

    companion object {
        lateinit var instance: MoviesApplication
    }

    init {
        instance = this
    }
}