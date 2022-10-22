package com.udacity.shoestore.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.udacity.shoestore.other.Constants.KEY_FIRST_TIME_OPEN
import com.udacity.shoestore.other.Constants.KEY_LOGGED_IN_STATE
import com.udacity.shoestore.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//what put here will be injected
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideFirstTimeOPEN(sharedPreferences: SharedPreferences) =
        sharedPreferences.getBoolean(KEY_FIRST_TIME_OPEN, true)

//    @Singleton
//    @Provides
//    fun provideLoggedInState(sharedPreferences: SharedPreferences) =
//        sharedPreferences.getBoolean(KEY_LOGGED_IN_STATE, false)
}