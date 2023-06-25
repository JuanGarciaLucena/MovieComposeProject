package com.emebesoft.movieProject.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emebesoft.movieProject.data.database.entity.RickMortyCharacterEntity

@Dao
interface RickMortyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characterList: List<RickMortyCharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSingleCharacter(character: RickMortyCharacterEntity)

    @Query("SELECT * FROM character_table")
    suspend fun getCharactersFromDatabase(): List<RickMortyCharacterEntity>

    @Query("SELECT * FROM character_table WHERE id = :id")
    suspend fun getCharacterById(id: String) : RickMortyCharacterEntity
}
