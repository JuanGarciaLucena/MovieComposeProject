package com.emebesoft.movieProject.data.network

import com.emebesoft.movieProject.data.model.CharacterModel
import retrofit2.http.GET

interface RickMortyRetrofitApi {

    @GET("character")
    suspend fun getAllCharacters() : CharacterModel
}