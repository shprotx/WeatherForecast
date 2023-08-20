package ru.shprot.friendlyweather.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun providePrefs(application: Application): SharedPreferences {
        return application.getSharedPreferences("WeatherPreferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideEditor(application: Application): Editor {
        return providePrefs(application).edit()
    }

}