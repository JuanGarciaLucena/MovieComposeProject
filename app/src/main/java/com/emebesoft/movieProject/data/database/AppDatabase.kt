package com.emebesoft.movieProject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emebesoft.movieProject.data.database.dao.ExampleDao
import com.emebesoft.movieProject.data.database.entity.ExampleEntity

@Database(entities = [ExampleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase:  RoomDatabase() {

    abstract fun getAllDao(): ExampleDao
}