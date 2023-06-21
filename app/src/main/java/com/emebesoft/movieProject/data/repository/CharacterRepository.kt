package com.emebesoft.movieProject.data.repository

import com.emebesoft.movieProject.data.database.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<List<CharacterEntity>>
}