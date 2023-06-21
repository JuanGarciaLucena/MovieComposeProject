package com.emebesoft.movieProject.di

import com.emebesoft.baseProject.BuildConfig
import com.emebesoft.movieProject.data.network.RickMortyRetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesRMApiClient(retrofit: Retrofit) : RickMortyRetrofitApi{
        return retrofit.create(RickMortyRetrofitApi::class.java)
    }
}