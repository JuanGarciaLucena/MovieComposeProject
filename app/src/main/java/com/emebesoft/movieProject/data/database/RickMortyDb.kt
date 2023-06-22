package com.emebesoft.movieProject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emebesoft.movieProject.data.database.dao.RickMortyDao
import com.emebesoft.movieProject.data.database.entity.CharacterEntity
import com.emebesoft.movieProject.utils.DataConverter


@Database(entities = [CharacterEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class RickMortyDb: RoomDatabase() {

    abstract fun getRickMortyDao(): RickMortyDao
}