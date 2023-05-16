package com.emebesoft.movieProject.data.database

import androidx.room.Database
import com.emebesoft.movieProject.data.database.dao.ExampleDao
import com.emebesoft.movieProject.data.database.entity.ExampleEntity

@Database(entities = [ExampleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase {

    abstract fun getAllDao(): ExampleDao
}