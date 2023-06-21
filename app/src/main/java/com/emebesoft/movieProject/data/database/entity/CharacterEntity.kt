package com.emebesoft.movieProject.data.database.entity

import androidx.room.ColumnInfo

data class CharacterEntity (

    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "episode") val episode: List<String>,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "created") val created: String,
    @ColumnInfo(name = "locationName") val locationName: String,
    @ColumnInfo(name = "locationUrl") val locationUrl: String,
    @ColumnInfo(name = "originName") val originName: String,
    @ColumnInfo(name = "originUrl") val originUrl: String
)
