package com.e.currencyconverter

import com.e.currencyconverter.API.FixerApiEndPoint
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Singleton
     @Provides
    fun provideRetrofitBuilder():Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    }

    @Singleton
    @Provides
    fun provideFixerApiEndPoint (retrofit: Retrofit.Builder): FixerApiEndPoint {
    return retrofit.build().create(FixerApiEndPoint::class.java)
    }


}