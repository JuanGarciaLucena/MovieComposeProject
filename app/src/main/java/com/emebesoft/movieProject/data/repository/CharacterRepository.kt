package com.emebesoft.movieProject.data.repository

import com.emebesoft.movieProject.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<List<Character>>
    suspend fun getCharacter(characterId: String): Flow<Character>
}