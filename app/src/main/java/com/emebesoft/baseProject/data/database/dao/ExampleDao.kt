package com.emebesoft.baseProject.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.emebesoft.baseProject.data.database.entity.ExampleEntity

@Dao
interface ExampleDao {

    @Query("SELECT * FROM example_table")
    suspend fun getAll(): List<ExampleEntity>
}