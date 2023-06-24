package com.emebesoft.movieProject.data.repository

import com.emebesoft.movieProject.data.database.entity.CharacterEntity
import com.emebesoft.movieProject.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<List<CharacterEntity>>
    suspend fun getCharacter(characterId: String): Flow<CharacterEntity>
}