package com.yilmazvolkan.moviesapp.di

import com.yilmazvolkan.moviesapp.app.MoviesApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(): MoviesApplication = MoviesApplication.instance

}