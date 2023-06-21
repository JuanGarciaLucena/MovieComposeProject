package com.emebesoft.movieProject.data.repository

import com.emebesoft.movieProject.data.database.entity.CharacterEntity
import com.emebesoft.movieProject.data.network.RickMortyDataSource
import com.emebesoft.movieProject.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val rickMortyDataSource: RickMortyDataSource) :
    CharacterRepository {

    override fun getCharacters(): Flow<List<CharacterEntity>> =
        rickMortyDataSource.allCharacters.map { characterList ->
            characterList.map { characterItem ->
                CharacterEntity(
                    id = characterItem.id,
                    name = characterItem.name,
                    status = characterItem.status,
                    species = characterItem.species,
                    type = characterItem.type,
                    gender = characterItem.gender,
                    image = characterItem.image,
                    episode = characterItem.episode,
                    url = characterItem.url,
                    created = characterItem.created,
                    locationName = characterItem.location.name,
                    locationUrl = characterItem.location.url,
                    originName = characterItem.origin.name,
                    originUrl = characterItem.origin.url
                )
            }
        }

    suspend fun saveCharactersToDatabase(characters: List<CharacterEntity>){

    }
}