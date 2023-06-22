package com.emebesoft.movieProject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emebesoft.movieProject.data.database.entity.CharacterEntity

@Dao
interface RickMortyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characterList: List<CharacterEntity>)

    @Query("SELECT * FROM character_table")
    suspend fun getCharactersFromDatabase(): List<CharacterEntity>
}
