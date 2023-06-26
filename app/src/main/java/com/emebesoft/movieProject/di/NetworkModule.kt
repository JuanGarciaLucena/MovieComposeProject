package com.emebesoft.movieProject.di

import com.emebesoft.baseProject.BuildConfig
import com.emebesoft.movieProject.data.network.RickMortyRetrofitApi
import com.emebesoft.movieProject.utils.Constants.Companion.TIMEOUT_SECONDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private fun configureHttpClient() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(configureHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesRMApiClient(retrofit: Retrofit) : RickMortyRetrofitApi{
        return retrofit.create(RickMortyRetrofitApi::class.java)
    }
}