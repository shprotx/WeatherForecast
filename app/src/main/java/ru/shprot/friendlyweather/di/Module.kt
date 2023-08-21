package ru.shprot.friendlyweather.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import ru.shprot.friendlyweather.common.DataProvider
import ru.shprot.friendlyweather.viewModel.MainViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

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

    @Provides
    @Singleton
    fun provideDataProvider(): DataProvider {
        return DataProvider()
    }

    @Provides
    @Singleton
    fun provideViewModel(): MainViewModel {
        return MainViewModel()
    }

}