package com.emebesoft.movieProject.data.network

import com.emebesoft.movieProject.data.model.CharacterModelResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RickMortyDataSource @Inject constructor(private val api: RickMortyRetrofitApi) {

    suspend fun fetchCharactersFromApi() = api.getAllCharacters().results
}