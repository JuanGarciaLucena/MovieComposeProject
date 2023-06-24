package com.emebesoft.movieProject.di

import android.content.Context
import androidx.room.Room
import com.emebesoft.movieProject.data.database.RickMortyDb
import com.emebesoft.movieProject.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, RickMortyDb::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRickMortyDao(db: RickMortyDb) = db.getRickMortyDao()
}