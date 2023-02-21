package com.cblanco.rickandmortychars.core.di

import android.app.Application
import android.content.Context
import com.cblanco.rickandmortychars.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    companion object {
        const val nameApp = "nameApp"
    }



    @Provides
    @Singleton
    fun providesContext(context: Application): Context {
        return context.applicationContext
    }

    @Provides
    @Singleton
    @Named(nameApp)
    fun provideNameApp(context: Context): String {
        return context.getString(R.string.app_name)
    }
}