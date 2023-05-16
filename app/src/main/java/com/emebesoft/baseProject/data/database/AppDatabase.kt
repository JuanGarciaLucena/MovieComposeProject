package com.emebesoft.baseProject.data.database

import androidx.room.Database
import com.emebesoft.baseProject.data.database.dao.ExampleDao
import com.emebesoft.baseProject.data.database.entity.ExampleEntity

@Database(entities = [ExampleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase {

    abstract fun getAllDao(): ExampleDao
}