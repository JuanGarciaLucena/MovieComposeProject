package com.emebesoft.movieProject.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example_table")
data class ExampleEntity (

    @PrimaryKey
    @ColumnInfo(name= "examplePrimaryKey") val examplePrimaryKey: String
)